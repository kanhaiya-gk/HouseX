import React from "react";

const Appointment_tag = (props) => {
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

  const handleBuyer = () => {
    fetch(`${process.env.REACT_APP_URL}/appointments/buyer`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        props.handleTagAppointments(data);
        props.handleStatus("As Buyer");
      });
  };

  const handleSeller = () => {
    fetch(`${process.env.REACT_APP_URL}/appointments/seller`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        props.handleTagAppointments(data);
        props.handleStatus("As Seller");
      });
  };

  return (
    <section class="tag-filter" onClick={handleEvent} data-dropdown>
      <button
        class="filter-btn filter-btn--full"
        id="filter-btn"
        data-dropdown-button
      >
        {props.status}
        <span class="down-arrow">&#x25BC;</span>
      </button>
      <div class="filter-menu filter-menu--full">
        <div class="filter-item" onClick={handleBuyer}>
          <button className="bookmark-icon-para bookmark-icon-para--full">
            <p class="bookmark-para bookmark-para--full">As Buyer</p>
          </button>
        </div>

        <div class="filter-item" onClick={handleSeller}>
          <button class="bookmark-icon-para bookmark-icon-para--full">
            <p class="bookmark-para bookmark-para--full">As Seller</p>
          </button>
        </div>
      </div>
    </section>
  );
};

export default Appointment_tag;
