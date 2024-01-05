import React, { useEffect, useState } from "react";

const Search_2 = (props) => {
  const [house, setHouse] = useState({});

  useEffect(() => {
    setHouse(props.house);
  }, [props.house]);

  const handleAddBookmark = () => {
    const house_id = house.house_id;
    const house_data = {
      house_id: house_id,
    };
    fetch(`${process.env.REACT_APP_URL}/houses/bookmark`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(house_data),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("added house", data);
      });
  };

  const handleRemoveBookmark = () => {
    console.log("Hello kadu");
    const house_id = house.house_id;
    const house_data = {
      house_id: house_id,
    };
    fetch(`${process.env.REACT_APP_URL}/houses/bookmark`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(house_data),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("removed house", data);
      });
  };

  const handleIconBookmark = (e) => {
    e.stopPropagation();
    const bookmark_icon = e.currentTarget.childNodes[0];
    if (bookmark_icon.classList.contains("bookmark-icon--full")) {
      bookmark_icon.classList.remove("bookmark-icon--full");
      handleRemoveBookmark();
    } else {
      bookmark_icon.classList.add("bookmark-icon--full");
      handleAddBookmark();
    }
  };

  const handleSearchOneHouse = (e) => {
    window.location.href = `${process.env.REACT_APP_REACT_URL}/house_info/${
      house.house_id
    }?token=${localStorage.getItem("token")}`;
  };

  return (
    <div class="house" onClick={handleSearchOneHouse}>
      <div class="bookmark-icon-container-container">
        <button class="bookmark-icon-container" onClick={handleIconBookmark}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            class={`w-6 h-6 bookmark-icon ${
              props.house.bookmark === true ? "bookmark-icon--full" : ""
            }`}
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z"
            />
          </svg>
        </button>
      </div>
      <div class="house-img-container">
        <img class="house-img" src={house.front} alt="Image of House" />
      </div>
      <div class="house-content">
        <ul class="house-attributes">
          <li class="house-attribute">
            <svg
              class="house-icon w-6 h-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="{1.5}"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15 8.25H9m6 3H9m3 6l-3-3h1.5a3 3 0 100-6M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span>
              <strong>{house.rent} </strong>per month
            </span>
          </li>
          <li class="house-attribute">
            <svg
              class="house-icon w-6 h-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M16.5 8.25V6a2.25 2.25 0 00-2.25-2.25H6A2.25 2.25 0 003.75 6v8.25A2.25 2.25 0 006 16.5h2.25m8.25-8.25H18a2.25 2.25 0 012.25 2.25V18A2.25 2.25 0 0118 20.25h-7.5A2.25 2.25 0 018.25 18v-1.5m8.25-8.25h-6a2.25 2.25 0 00-2.25 2.25v6"
              />
            </svg>
            <span>
              <strong>{house.area} </strong>sq. meter
            </span>
          </li>

          <li class="house-attribute">
            <svg
              class="house-icon w-6 h-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
              />
            </svg>
            <span>
              <strong>{house.bhk} </strong>BHK
            </span>
          </li>

          <li class="house-attribute">
            <svg
              class="house-icon w-6 h-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15 10.5a3 3 0 11-6 0 3 3 0 016 0z"
              />
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M19.5 10.5c0 7.142-7.5 11.25-7.5 11.25S4.5 17.642 4.5 10.5a7.5 7.5 0 1115 0z"
              />
            </svg>
            <span>
              <strong>{house.city} </strong>
            </span>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Search_2;
