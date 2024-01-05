import React, { useEffect, useState } from "react";
import Search_4_Buyer from "./Search_4_Buyer";
import Search_4_Seller from "./Search_4_Seller";

const Search_3 = () => {
  const [info, setInfo] = useState(null);
  const [index, setIndex] = useState(0);
  const [no_images, setNo_images] = useState(0);
  const [front, setFront] = useState("");
  const [slots, setSlots] = useState([]);
  const [images, setImages] = useState([]);

  const addSlotInSlots = (slot) => {
    const tempSlots = [...slots, slot];
    setSlots(tempSlots);
  };

  const deleteSlotInSlots = (slot_id) => {
    const tempSlots = slots.filter((slot) => slot !== slot_id);
    setSlots(tempSlots);
  };

  useEffect(() => {
    get_house();
  }, []);

  const get_house = () => {
    const house_id = window.location.pathname.replace("/house_info/", "");
    fetch(`${process.env.REACT_APP_URL}/houses/${house_id}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setInfo(data);
        setFront(data.house.front);
        setImages([...data.images, data.house.front]);
        setNo_images(data.images.length + 1);
        setSlots(data.dates);
      });
  };

  const handleLeftImg = () => {
    const tempIndex = (index - 1 + no_images) % no_images;
    setIndex(tempIndex);
    setFront(images[tempIndex]);
  };

  const handleRightImg = () => {
    const tempIndex = (index + 1) % no_images;
    setIndex(tempIndex);
    setFront(images[tempIndex]);
  };

  const handleDeleteHouse = () => {
    fetch(`${process.env.REACT_APP_URL}/houses/${info.house.house_id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        window.location.href = `${process.env.REACT_APP_REACT_URL}/search`;
      });
  };

  return (
    <section class="section-housefull">
      {info !== null && (
        <div class="housefull">
          <div class="housefull-img-container">
            <div class="housefull-img-left-container">
              <button
                class="housefull-img-left iterator-icon"
                onClick={handleLeftImg}
              >
                &#60;
              </button>
            </div>
            <img src={front} alt="House Image" class="housefull-img" />
            <div class="housefull-img-right-container">
              <button
                class="housefull-img-right iterator-icon"
                onClick={handleRightImg}
              >
                &#62;
              </button>
            </div>

            {info.owner === true ? (
              <Search_4_Seller
                slots={slots}
                house_id={info.house.house_id}
                addSlotInSlots={addSlotInSlots}
                deleteSlotInSlots={deleteSlotInSlots}
              />
            ) : (
              <Search_4_Buyer
                slots={info.dates}
                house_id={info.house.house_id}
              />
            )}
          </div>
          <div class="housefull-text">
            {info.owner === true && (
              <button class="house-delete-icon-btn">
                <svg
                  onClick={handleDeleteHouse}
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  class="w-6 h-6 house-delete-icon"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M12 9.75L14.25 12m0 0l2.25 2.25M14.25 12l2.25-2.25M14.25 12L12 14.25m-2.58 4.92l-6.375-6.375a1.125 1.125 0 010-1.59L9.42 4.83c.211-.211.498-.33.796-.33H19.5a2.25 2.25 0 012.25 2.25v10.5a2.25 2.25 0 01-2.25 2.25h-9.284c-.298 0-.585-.119-.796-.33z"
                  />
                </svg>
              </button>
            )}
            <div class="housefull-item housefull-address">
              <div class="attribute-tag">
                <span class="value-tag--large">{info.house.street}</span>
                <span class="key-tag--large">ADDRESS</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.city}</span>
                <span class="key-tag">CITY</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.state}</span>
                <span class="key-tag">STATE</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.end_date}</span>
                <span class="key-tag">END DATE</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.bhk}</span>
                <span class="key-tag">BHK</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.area}</span>
                <span class="key-tag">AREA</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.house.construction_year}</span>
                <span class="key-tag">CONSTRUCTION YEAR</span>
              </div>
            </div>

            <div class="housefull-item housefull-price">
              <div class="attribute-tag">
                <span class="value-tag--large">&#8377; {info.house.rent}</span>
                <span class="key-tag--large">PRICE</span>
              </div>
            </div>

            <div class="housefull-desc">{info.house.house_description}</div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.name}</span>
                <span class="key-tag">NAME</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.phone_no}</span>
                <span class="key-tag">PHONE</span>
              </div>
            </div>

            <div class="housefull-item">
              <div class="attribute-tag">
                <span class="value-tag">{info.email_id}</span>
                <span class="key-tag">EMAIL</span>
              </div>
            </div>
          </div>
        </div>
      )}
    </section>
  );
};

export default Search_3;
