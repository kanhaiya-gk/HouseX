import React, { useEffect, useState } from "react";
import Search2 from "./Search_2";
import Filter from "./Filter";
import Bookmark from "./Bookmark";

const Search = () => {
  const [houses, setHouses] = useState([]);

  const loadHouses = () => {
    fetch(`${process.env.REACT_APP_URL}/houses`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (Array.isArray(data)) {
          setHouses(data);
        }
      });
  };

  useEffect(() => {
    loadHouses();
  }, []);

  const handleFilterHouses = (filtered_houses) => {
    setHouses(filtered_houses);
  };

  const handleTagHouses = (tag_houses) => {
    setHouses(tag_houses);
  };

  return (
    <section class="section-houses">
      <div class="conatiner center-text">
        <span class="subheading">Houses</span>
        <Bookmark handleTagHouses={handleTagHouses} />
        <Filter handleFilterHouses={handleFilterHouses} />
        <h2 class="heading-secondary houses-heading">
          Search available houses
        </h2>
      </div>
      <div class="container grid grid--3-cols margin-bottom-md">
        {/* <a class="house-link" href="#"> */}
        {houses.map((house) => {
          return <Search2 key={house.house_id} house={house} />;
        })}
        {/* </a> */}
      </div>
    </section>
  );
};

export default Search;
