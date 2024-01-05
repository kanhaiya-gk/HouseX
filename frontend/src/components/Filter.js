import React, { useState } from "react";

const Filter = (props) => {
  
  const handleEvent = (e) => {
    const isDropdownButton = e.target.matches("[data-dropdown-button]");
    if (!isDropdownButton && e.target.closest("[data-dropdown]") != null)
      return;

    let currentDropdown;
    if (isDropdownButton) {
      currentDropdown = e.target.closest("[data-dropdown]");
      currentDropdown.classList.toggle("active");
    }

    document.querySelectorAll("[data-dropdown].active").forEach((dropdown) => {
      if (dropdown === currentDropdown) return;
      dropdown.classList.remove("active");
    });
  };

  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [bhk, setBhk] = useState("");
  const [rent, setRent] = useState("");
  const [months, setMonths] = useState("");

  const handleCity = (e) => {
    setCity(e.target.value);
  };

  const handleState = (e) => {
    setState(e.target.value);
  };

  const handleBhk = (e) => {
    setBhk(e.target.value);
  };

  const handleRent = (e) => {
    setRent(e.target.value);
  };

  const handleMonths = (e) => {
    setMonths(e.target.value);
  };

  const handleFilter = () => {
    const filter_data = {};

    if (city !== "") {
      filter_data["city"] = city;
    }
    if (state !== "") {
      filter_data["state"] = state;
    }
    if (bhk !== "") {
      filter_data["bhk"] = bhk;
    }
    if (rent !== "") {
      filter_data["rent"] = rent;
    }
    if (months !== "") {
      filter_data["months"] = months;
    }

    console.log("filter_data", filter_data);

    fetch(`${process.env.REACT_APP_URL}/houses/filter`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(filter_data),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        props.handleFilterHouses(data);
      });
  };

  return (
    <section class="house-filter" onClick={handleEvent} data-dropdown>
      <button
        class="filter-btn filter-btn--full"
        id="filter-btn"
        data-dropdown-button
      >
        Add Filter <span class="down-arrow">&#x25BC;</span>
      </button>
      <div class="filter-menu">
        <div class="filter-item">
          <div class="filter-icon-input">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
              class="w-6 h-6 filter-icon"
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
            <input
              onChange={handleCity}
              type="text"
              class="filter-city"
              placeholder="In which city"
            />
          </div>
        </div>

        <div class="filter-item">
          <div class="filter-icon-input">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
              class="w-6 h-6 filter-icon"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M9 6.75V15m6-6v8.25m.503 3.498l4.875-2.437c.381-.19.622-.58.622-1.006V4.82c0-.836-.88-1.38-1.628-1.006l-3.869 1.934c-.317.159-.69.159-1.006 0L9.503 3.252a1.125 1.125 0 00-1.006 0L3.622 5.689C3.24 5.88 3 6.27 3 6.695V19.18c0 .836.88 1.38 1.628 1.006l3.869-1.934c.317-.159.69-.159 1.006 0l4.994 2.497c.317.158.69.158 1.006 0z"
              />
            </svg>
            <input
              onChange={handleState}
              type="text"
              class="filter-email"
              placeholder="In which state"
            />
          </div>
        </div>

        <div class="filter-item">
          <div class="filter-icon-input">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
              class="w-6 h-6 filter-icon"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
              />
            </svg>

            <input
              onChange={handleBhk}
              type="text"
              class="filter-bhk"
              placeholder="How many bhk's"
            />
          </div>
        </div>

        <div class="filter-item">
          <div class="filter-icon-input">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
              class="w-6 h-6 filter-icon"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 6v12m-3-2.818l.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>

            <input
              onChange={handleRent}
              type="text"
              class="filter-rent"
              placeholder="How much rent"
            />
          </div>
        </div>

        <div class="filter-item">
          <div class="filter-icon-input">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth="1.5"
              stroke="currentColor"
              class="w-6 h-6 filter-icon"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 6v6h4.5m4.5 0a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>

            <input
              onChange={handleMonths}
              type="text"
              class="filter-months"
              placeholder="Months to stay"
            />
          </div>
        </div>

        <div class="filter-check-container">
          <button
            class="apply-filter-btn apply-filter-btn--full"
            onClick={handleFilter}
          >
            Apply Filter
          </button>
          {/* <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            class="w-6 h-6 filter-check"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M9 12.75L11.25 15 15 9.75M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
            />
          </svg> */}
        </div>
      </div>
    </section>
  );
};

export default Filter;
