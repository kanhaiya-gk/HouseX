import React, { useState } from "react";
import Search_5_Buyer from "./Search_5_Buyer";

const Search_4_Buyer = (props) => {
  const [slot_id, setSlot_id] = useState(null);

  const selectSlot = (which_slot_id) => {
    setSlot_id(which_slot_id);
    console.log("which_slot_id", which_slot_id);
  };

  const handleBookSlot = () => {
    const book_appointment_form_data = {
      house_id: props.house_id,
      appoint_day: slot_id,
    };

    fetch(`${process.env.REACT_APP_URL}/appointments`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(book_appointment_form_data),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.message !== "success") {
          alert("Appointment already booked");
        }
        window.location.href = `${process.env.REACT_APP_REACT_URL}/appointment`;
      });
  };

  return (
    <section class="section-book-appointment">
      <div class="appointment-dates-container">
        {props.slots.map((slot) => {
          return (
            <Search_5_Buyer
              key={slot}
              slot={slot}
              selectSlot={selectSlot}
              selectedSlotId={slot_id}
            />
          );
        })}
      </div>
      <button class="book-btn book-btn--full" onClick={handleBookSlot}>
        Book Appointment &#40;Select from given dates&#41;
      </button>
    </section>
  );
};

export default Search_4_Buyer;
