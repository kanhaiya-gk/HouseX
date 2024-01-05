package com.example.dbms.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.dbms.entities.CustomHouse;
import com.example.dbms.entities.FilterObject;
import com.example.dbms.entities.House;
import com.example.dbms.entities.Image;
import com.example.dbms.entities.ImageObject;
import com.example.dbms.entities.Slots;
import com.example.dbms.entities.UserEntity;
import com.example.dbms.entities.bookmarked;


@Component

public class HouseJdbcDAO{

	@Autowired
	DataSource datasource;

	private static final Logger log = LoggerFactory.getLogger(HouseJdbcDAO.class);
	private JdbcTemplate jdbctemplate;
// //	@Autowired
	public HouseJdbcDAO(DataSource datasource) {
		super();
		this.jdbctemplate = new JdbcTemplate(datasource);
	}

	
	RowMapper<House> rowmapper2 = (rs, rowNum)->{
		House house = new House();
		house.setHouse_id(rs.getInt("house_id"));
		house.setArea(rs.getDouble("area"));
		house.setConstruction_year(rs.getInt("construction_year"));
		house.setStreet(rs.getString("street"));
		house.setCity(rs.getString("city"));
		house.setState(rs.getString("state"));
		house.setHouse_description(rs.getString("house_description"));
		house.setBhk(rs.getInt("bhk"));
		house.setRent(rs.getInt("rent"));
		house.setEnd_date(rs.getDate("end_date"));
		house.setEmail_id(rs.getString("email_id"));
		house.setRating(rs.getDouble("rating"));
		house.setIsoccupied(rs.getBoolean("isoccupied"));
		Blob front=rs.getBlob("front");
		if(front!=null) {
			byte[] bdata = front.getBytes(1, (int) front.length());
			String s = new String(bdata);
			house.setFront(s);
		}
		return house;
	};
	
	RowMapper<Image> rowmapperimage = (rs, rowNum)->{
		Image img = new Image();
		img.setHouse_id(rs.getInt("house_id"));
		img.setImage_id(rs.getInt("image_id"));
		Blob image=rs.getBlob("house_image");
		if(image != null) {
			byte[] bdata = image.getBytes(1, (int) image.length());
			String s = new String(bdata);
			img.setHouse_image(s);
		}
		return img;
	};
	
	RowMapper<bookmarked> rowmapperbookmark = (rs, rowNum)->{
		bookmarked bookmark=new bookmarked();
		bookmark.setHouse_id(rs.getInt("house_id"));
		bookmark.setEmail_id(rs.getString("email_id"));
		return bookmark;
	};

	RowMapper<UserEntity> rowMapperOwner = (rs,rowNum) -> {
		UserEntity userN = new UserEntity();
        userN.setName(rs.getString("name"));
        // userN.setPassword(rs.getString("password"));
        // userN.setRole(rs.getString("role"));
        // userN.setEnabled(rs.getBoolean("enabled"));
        userN.setEmail_id(rs.getString("email_id"));
        userN.setPhone_no(rs.getString("phone_no"));
        // userN.setRating(rs.getDouble("rating"));
        return userN;
	};

	public List<CustomHouse> list() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();

		String sql="select * from house where isoccupied=false";
		String sq="select * from bookmark where email_id=?";
		List<House> allHouses = jdbctemplate.query(sql, rowmapper2);
		List<bookmarked> bookmark_houses=jdbctemplate.query(sq, rowmapperbookmark,new Object[]{name});
		List<CustomHouse> custom=new ArrayList<>();
		for(House h: allHouses){
			int hid=h.getHouse_id();
			boolean flag=false;
			for(bookmarked b:bookmark_houses){
				if(b.getHouse_id()==hid){
					flag=true;
					break;
				}
			}
			CustomHouse c=new CustomHouse(h.getHouse_id(),flag,h.getRent(),h.getArea(),h.getBhk(),h.getCity(),h.getFront());
			custom.add(c);
		}
		return custom;
	}

	// public List<House> housecity(String city){
	// 	String sql = "select * from house where city = ?";
	// 	List<House> city_home = null;
	// 	try {
	// 		city_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{city});
	// 	}
	// 	catch(DataAccessException daex) {
	// 		log.info("house not found : " + city);
	// 	}
		
	// 	return city_home;
	// }
	
	public List<CustomHouse> myhouse(String email_id){
		String sql = "select * from house where email_id = ?";
		
		List<House> my_home = null;
		List<CustomHouse> custom=new ArrayList<>();
		try {
			my_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{email_id});
			String sq="select * from bookmark where email_id=?";
			List<bookmarked> bookmark_houses=jdbctemplate.query(sq, rowmapperbookmark,new Object[]{email_id});
			
			for(House h: my_home){
				int hid=h.getHouse_id();
				boolean flag=false;
				for(bookmarked b:bookmark_houses){
					if(b.getHouse_id()==hid){
						flag=true;
						break;
					}
				}
				CustomHouse c=new CustomHouse(h.getHouse_id(),flag,h.getRent(),h.getArea(),h.getBhk(),h.getCity(),h.getFront());
				custom.add(c);
			}
		}
		catch(DataAccessException daex) {
			log.info("You have not currently registered any house");
		}   
		return custom;
	}
	
	public void create(ImageObject i) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
//		System.out.println("image --"+name+"------------------");
		House t=i.getHouse();
		String sql = "insert into house(area, construction_year, street, city, state, house_description, bhk, rent, end_date, email_id, isoccupied, front) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		GeneratedKeyHolder key = new GeneratedKeyHolder();

		jdbctemplate.update(conn -> {
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setDouble(1,t.getArea());
			st.setInt(2, t.getConstruction_year());
			st.setString(3, t.getStreet());
			st.setString(4, t.getCity());
			st.setString(5, t.getState());
			st.setString(6, t.getHouse_description());
			st.setInt(7, t.getBhk());
			st.setInt(8, t.getRent());
			st.setDate(9, t.getEnd_date());
			st.setString(10, name);
            //st.setDouble(11, 0.0);
			st.setBoolean(11, false);
			if(t.getFront()!=null){
				String strContent = t.getFront();
				byte[] byteContent = strContent.getBytes();
				Blob blob = conn.createBlob();
				blob.setBytes(1, byteContent);
				st.setBlob(12, blob);
			}
			else{
				Blob blob = conn.createBlob();
				st.setBlob(12, blob);
			}
			return st;
		}, key);

		int id = key.getKey().intValue();

		// jdbctemplate.update(sql, t.getArea(), t.getConstruction_year(), t.getStreet(), t.getCity(), t.getState(), t.getHouse_description(), t.getBhk(), t.getRent(), t.getEnd_date(), t.getUser_id(), t.getIsoccupied(), t.getFront());
		// String sq = "select * from house where area=? and construction_year=? and street=? and city=? and state=? and house_description=? and bhk=? and rent=? and user_id=? and isoccupied=?";
		// House h = jdbctemplate.queryForObject(sq,rowmapper2, new Object[]{t.getArea(), t.getConstruction_year(), t.getStreet(), t.getCity(), t.getState(), t.getHouse_description(), t.getBhk(), t.getRent(), t.getUser_id(), t.getIsoccupied()});
		for(String image:i.getImages()) {
			String sq1 = "insert into image(house_id, house_image) values(?, ?);";
			String strContent = image;
		    byte[] byteContent = strContent.getBytes();
				Connection con = null;
			try {
				con = DataSourceUtils.getConnection(datasource);
				// con = jdbctemplate.getDataSource().getConnection();
				Blob blob = con.createBlob();//Where connection is the connection to db object.
				blob.setBytes(1, byteContent);
				jdbctemplate.update(sq1, id, blob);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				if(con!=null) {
					DataSourceUtils.releaseConnection(con, datasource);
				}
			}
		}
	}

	public ImageObject get(int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String sql = "select * from house where house_id = ?";
		House home = null;
		home = jdbctemplate.queryForObject(sql,rowmapper2, new Object[]{id});
		String sq = "select * from image where house_id = ?";
		List<Image> list = jdbctemplate.query(sq,rowmapperimage, new Object[]{id});
		List<String> ls = new ArrayList<String>();
		for(Image i:list) {
			ls.add(i.getHouse_image());
		}
		UserEntity owner = null;
		String sqlowner = "select email_id,phone_no,name from userEntity where email_id = ?";
		owner = jdbctemplate.queryForObject(sqlowner, rowMapperOwner,new Object[]{home.getEmail_id()});
		
		List<Date> ld = new ArrayList<Date>();
		String sqlSlot = "select * from slots where house_id = ?";
		List<Slots> slots = jdbctemplate.query(sqlSlot,(rs,rowNum)-> new Slots(rs.getInt("house_id"),rs.getDate("date")), new Object[]{id});
		for(Slots i:slots) {
			ld.add(i.getDate());
		}
		boolean isowner=false;
		if(home.getEmail_id().equals(name)){
			isowner=true;
		}
		ImageObject img = new ImageObject(home, owner.getEmail_id(),owner.getPhone_no(),owner.getName(),ls,ld,isowner);
		return img;
	}

	public void update(ImageObject i, int id) {
		House t = i.getHouse();
		String sql = "update house set area = ?, construction_year = ?, street = ?, city = ? , state = ?, house_description = ?, bhk = ?, rent = ?, end_date= ?, email_id = ?, isoccupied = ?, front = ? where house_id = ?";
		jdbctemplate.update(sql, t.getArea(), t.getConstruction_year(), t.getStreet(), t.getCity(), t.getState(), t.getHouse_description(), t.getBhk(), t.getRent(), t.getEnd_date(), t.getEmail_id()
, t.getIsoccupied(), t.getFront(),id);
		jdbctemplate.update("delete from house where house_id = ? ", id);
		List<String> l =i.getImages();
		for(String img:l) {
			String sq = "insert into image(house_id, house_image) values(?, ?);";
			String strContent = img;
		    byte[] byteContent = strContent.getBytes();
			Connection con;
			try {
				con = jdbctemplate.getDataSource().getConnection();
				Blob blob = con.createBlob();//Where connection is the connection to db object.
				blob.setBytes(1, byteContent);
				jdbctemplate.update(sq, t.getHouse_id(), blob);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(int id) {
		jdbctemplate.update("delete from house where house_id = ? ", id);
	}

	// public List<House> housestate(String state) {
	// 	String sql = "select * from house where state = ?";
	// 	List<House> state_home = null;
	// 	try {
	// 		state_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{state});
	// 	}
	// 	catch(DataAccessException daex) {
	// 		log.info("house not found : " + state);
	// 	}
		     
	// 	return state_home;
	// }

	// public List<House> housebhk(int bhk) {
	// 	String sql = "select * from house where bhk = ?";
	// 	List<House> bhk_home = null;
	// 	try {
	// 		bhk_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{bhk});
	// 	}
	// 	catch(DataAccessException daex) {
	// 		log.info("house not found : " + bhk);
	// 	}
		     
	// 	return bhk_home;
	// }

	// public List<House> houserent(int rent) {
	// 	String sql = "select * from house where rent <= ?";
	// 	List<House> rent_home = null;
	// 	try {
	// 		rent_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{rent});
	// 	}
	// 	catch(DataAccessException daex) {
	// 		log.info("house not found : " + rent);
	// 	}
		     
	// 	return rent_home;
	// }

	// public List<House> houseDate(int months) {
	// 	LocalDate date = LocalDate.now().plusMonths(months);
	// 	String sql = "select * from house where end_date >= ?";
	// 	List<House> date_home = null;
	// 	try {
	// 		date_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{date});
	// 	}
	// 	catch(DataAccessException daex) {
	// 		log.info("house not found : " + date);
	// 	}
		     
	// 	return date_home;
	// }
	
	public List<CustomHouse> bookmarks(String email_id){ 
		String sql="select * from house where exists(select * from bookmark where bookmark.house_id = house.house_id and bookmark.email_id = ?)";
		List<CustomHouse> custom=new ArrayList<>();
		try {
			List<House> bookmark_home = (List<House>) jdbctemplate.query(sql,rowmapper2, new Object[]{email_id});

			for(House h: bookmark_home){
				CustomHouse c=new CustomHouse(h.getHouse_id(),true,h.getRent(),h.getArea(),h.getBhk(),h.getCity(),h.getFront());
				custom.add(c);
			}
		}
		catch(DataAccessException daex) {
			log.info("You have not currently registered any house");
		}   
		return custom;
	}
	
	public House returnhouse(int id) {
		String sql = "select * from house where house_id = ?";
		House home = null;
		home = jdbctemplate.queryForObject(sql,rowmapper2, new Object[]{id});
		return home;	
	}

	public List<CustomHouse> filter(FilterObject filter){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		List<House> filterhome = null;
		List<Object> obj=new ArrayList<Object>();
		String sql="select * from house where ";
		String state=filter.getState();
		String city=filter.getCity();
		Integer bhk=filter.getBhk();
		Integer rent=filter.getRent();
		Integer months=filter.getMonths();
		if(state!=null){
			sql=sql+"state = ? and ";
			obj.add(state);
		}
		if(city!=null){
			sql=sql+"city = ? and ";
			obj.add(city);
		}
		if(bhk!=null){
			sql=sql+"bhk = ? and ";
			obj.add(bhk);
		}
		if(rent!=null){
			sql=sql+"rent < ? and ";
			obj.add(rent);
		}
		if(months!=null){
			LocalDate date = LocalDate.now().plusMonths(months);
			sql=sql+"end_date >= ? and ";
			obj.add(date);
		}
		if(sql.endsWith("and ")){
			int len=sql.length()-4;
			String sq=sql.substring(0, len);
			sql=sq;
		}
		else if(sql.endsWith("where ")){
			int len=sql.length()-7;
			String sq=sql.substring(0, len);
			sql=sq;
		}
		int size=obj.size();
		Object[] objs=new Object[size];
		List<CustomHouse> custom=new ArrayList<>();
		for(int i=0;i<size;i++){
			objs[i]=obj.get(i);
		}
		try {
			filterhome = (List<House>) jdbctemplate.query(sql,rowmapper2, objs);

			String sq="select * from bookmark where email_id=?";
			List<bookmarked> bookmark_houses=jdbctemplate.query(sq, rowmapperbookmark,new Object[]{name});
			
			for(House h: filterhome){
				int hid=h.getHouse_id();
				boolean flag=false;
				for(bookmarked b:bookmark_houses){
					if(b.getHouse_id()==hid){
						flag=true;
						break;
					}
				}
				CustomHouse c=new CustomHouse(h.getHouse_id(),flag,h.getRent(),h.getArea(),h.getBhk(),h.getCity(),h.getFront());
				custom.add(c);
			}
		}
		catch(DataAccessException daex) {
			log.info("house not found : ");
		}
		return custom;
	}

}
