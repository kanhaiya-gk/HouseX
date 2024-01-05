import React, { useState } from "react";

const Upload = () => {
  const [area, setArea] = useState(0);
  const [year, setYear] = useState(0);
  const [street, setStreet] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [desc, setDesc] = useState("");
  const [bhk, setBhk] = useState(0);
  const [rent, setRent] = useState(0);
  const [end, setEnd] = useState("0000-00-00");
  const [front, setFront] = useState("");
  const [images, setImages] = useState([]);

  const handleRent = (e) => {
    setRent(e.target.value);
  };

  const handleArea = (e) => {
    setArea(e.target.value);
  };

  const handleYear = (e) => {
    setYear(e.target.value);
  };

  const handleBhk = (e) => {
    setBhk(e.target.value);
  };

  const handleStreet = (e) => {
    setStreet(e.target.value);
  };

  const handleCity = (e) => {
    setCity(e.target.value);
  };

  const handleState = (e) => {
    setState(e.target.value);
  };

  const handleDesc = (e) => {
    setDesc(e.target.value);
  };

  const handleEnd = (e) => {
    setEnd(e.target.value);
  };

  // const readFiles = (file, index) => {
  //   const reader = new FileReader();
  //   reader.onloadend = () => {
  //     setImages([...images, reader.result]);
  //     if (index == 0) {
  //       setFront(reader.result);
  //     }
  //   };
  //   reader.readAsDataURL(file);
  // };

  // const handleImages = (e) => {
  //   const files = [...e.target.files];
  //   files.map(async (file, index) => {
  //     await readFiles(file, index);
  //   });
  // };

  const handleFileChosen = async (file) => {
    return new Promise((resolve, reject) => {
      let fileReader = new FileReader();
      fileReader.onload = () => {
        resolve(fileReader.result);
      };
      fileReader.onerror = reject;
      fileReader.readAsDataURL(file);
    });
  };

  const readAllFiles = async (e) => {
    const AllFiles = [...e.target.files];
    const results = await Promise.all(
      AllFiles.map(async (file) => {
        const fileContents = await handleFileChosen(file);
        return fileContents;
      })
    );
    setImages(results.filter((result, index) => index !== 0));
    setFront(results[0]);
    return results;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const house_data = {
      house: {
        area: area,
        construction_year: year,
        street: street,
        city: city,
        state: state,
        house_description: desc,
        bhk: bhk,
        rent: rent,
        end_date: end,
        front: front,
      },
      images: images,
    };

    fetch(`${process.env.REACT_APP_URL}/houses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
      body: JSON.stringify(house_data),
    })
      .then((response) => response.json())
      .then((data) => console.log("House form submitted"));

    alert("Your house has been uploaded.");
  };

  return (
    <section class="section-cta">
      <div class="cta">
        <div class="cta-img-upload-box">
          <div class="cta-img-box">
            <img class="cta-img" src={front} alt="Image of house" />
          </div>
          <input
            onChange={readAllFiles}
            style={{ display: "none" }}
            type="file"
            name="image[]"
            id="files"
            multiple="true"
            placeholder="Upload image"
          ></input>
          <label for="files" class="cta-img-label">
            +
          </label>
          {/* <button class="cta-img-btn">+</button> */}
        </div>
        <div class="cta-text-box">
          <span class="subheading">Lease</span>
          <h2 class="cta-heading heading-secondary">
            Lease your house with no extra charge !
          </h2>
          <p class="cta-text">
            Get the best price available for your house and be up to date. Gain
            from the wide coverage of this platform. and do what not you know i
            dont have to tell you
          </p>
          <form action="#" class="cta-form" onSubmit={handleSubmit}>
            <div>
              <label for="rent">Rent price</label>
              <input
                onChange={handleRent}
                class="rent"
                name="rent"
                type="text"
                placeholder="$3200"
                required
              />
            </div>
            <div>
              <label for="area">Area of house</label>
              <input
                onChange={handleArea}
                type="text"
                class="area"
                name="area"
                placeholder="125 sq m"
              />
            </div>
            <div>
              <label for="year">Construction year</label>
              <input
                onChange={handleYear}
                type="text"
                class="year"
                name="year"
                placeholder="yyyy"
              />
            </div>
            <div>
              <label for="bhk">BHK of house</label>
              <input
                onChange={handleBhk}
                type="text"
                class="bhk"
                name="bhk"
                placeholder="2"
              />
            </div>
            <div>
              <label for="street">Street</label>
              <input
                onChange={handleStreet}
                type="text"
                class="street"
                name="street"
                placeholder="Indra Colony"
              />
            </div>
            <div>
              <label for="city">City</label>
              <input
                onChange={handleCity}
                type="text"
                class="city"
                name="city"
                placeholder="Bikaner"
              />
            </div>
            <div>
              <label for="state">State</label>
              <input
                onChange={handleState}
                type="text"
                class="state"
                name="state"
                placeholder="Rajasthan"
              />
            </div>
            <div>
              <label for="end_date">End Date</label>
              <input
                onChange={handleEnd}
                type="text"
                class="end_date"
                name="end_date"
                placeholder="2022-10-12"
              />
            </div>

            <div>
              <label for="info">Addition info</label>
              <input
                onChange={handleDesc}
                type="text"
                class="info"
                name="info"
                placeholder="Premium house"
              />
            </div>
            <button class="btn btn--full upload-btn">Submit</button>
          </form>
        </div>
      </div>
    </section>
  );
};

export default Upload;
