import React, { useEffect, useState } from "react";
import Search_5_Seller from "./Search_5_Seller";

const Search_4_Seller = (props) => {
  const [newSlot, setNewSlot] = useState("");
  const [slots, setSlots] = useState([]);

  useEffect(() => {
    setSlots(props.slots);
  }, [props.slots]);

  const handleNewSlot = (e) => {
    setNewSlot(e.target.value);
  };

  const handleAddSlot = () => {
    const add_slot_data = [
      {
        house_id: props.house_id,
        date: newSlot,
      },
    ];

    fetch(`${process.env.REACT_APP_URL}/myhouses/slots`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(add_slot_data),
    })
      .then((response) => response.json())
      .then((data) => {
        props.addSlotInSlots(newSlot);
      });
  };

  return (
    <section class="section-add-slots">
      <div class="add-slot-input-btn">
        <input
          class="add-slot-input"
          placeholder="Add slot"
          onChange={handleNewSlot}
        />
        <button class="add-slot-btn" onClick={handleAddSlot}>
          +
        </button>
      </div>
      <div class="appointment-dates-container">
        {slots.map((slot) => {
          return (
            <Search_5_Seller
              key={slot}
              slot={slot}
              deleteSlotInSlots={props.deleteSlotInSlots}
              slot_id={slot}
              house_id={props.house_id}
            />
          );
        })}
      </div>
    </section>
  );
};

export default Search_4_Seller;
