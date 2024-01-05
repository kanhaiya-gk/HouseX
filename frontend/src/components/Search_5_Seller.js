import React from "react";

const Search_5_Seller = (props) => {
  const handleRemoveSlot = () => {
    const delete_slot_data = {
      house_id: props.house_id,
      date: props.slot,
    };

    console.log(delete_slot_data);

    fetch(`${process.env.REACT_APP_URL}/myhouses/slots`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(delete_slot_data),
    })
      .then((response) => response.json())
      .then((data) => {
        props.deleteSlotInSlots(props.slot);
      });
  };

  return (
    <div class="select-appointment-btn select-appointment-btn--hide">
      <span class="select-appointment-date">{props.slot}</span>
      <button class="delete-slot-btn" onClick={handleRemoveSlot}>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-6 h-6 delete-slot-icon"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      </button>
    </div>
  );
};

export default Search_5_Seller;
