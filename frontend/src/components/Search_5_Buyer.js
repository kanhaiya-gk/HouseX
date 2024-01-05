import React, { useEffect, useState } from "react";

const Search_5_Buyer = (props) => {
  const [btn_style, setBtn_style] = useState("");

  const handleSelectSlot = (e) => {
    props.selectSlot(props.slot);
  };

  const initializeStyle = () => {
    if (props.slot === props.selectedSlotId) {
      setBtn_style("select-appointment-btn--full");
    } else {
      setBtn_style("");
    }
  };

  useEffect(() => {
    initializeStyle();
  }, [props.selectedSlotId]);

  return (
    <div
      class={`select-appointment-btn ${btn_style}`}
      onClick={handleSelectSlot}
    >
      <span class="select-appointment-date">{props.slot}</span>
    </div>
  );
};

export default Search_5_Buyer;
