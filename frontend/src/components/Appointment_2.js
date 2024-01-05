import React from "react";

const Appointment_2 = (props) => {
  const handleSeeHouse = () => {
    window.location.href = `${process.env.REACT_APP_REACT_URL}/house_info/${
      props.appointment.house_id
    }?token=${localStorage.getItem("token")}`;
  };

  const handleDeleteAppointment = (e) => {
    e.stopPropagation();
    const delete_appointment_data = {
      house_id: props.appointment.house_id,
      appoint_day: props.appointment.appoint_day,
    };

    fetch(`${process.env.REACT_APP_URL}/appointments`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(delete_appointment_data),
    })
      .then((response) => response.json())
      .then((data) => {
        props.deleteAppointmentFromChild(props.appointment_id);
      });
  };

  return (
    <div class="appointment" onClick={handleSeeHouse}>
      <button class="house-delete-icon-btn" onClick={handleDeleteAppointment}>
        <svg
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
      <div class="appointment-img-container">
        <img
          src={props.appointment.front}
          alt="House Image"
          class="appointment-img"
        />
      </div>
      <div class="appointment-text">
        <ul class="appointment-list">
          <li class="appointment-item">
            <div class="attribute-tag">
              <span class="appointment-name">
                {props.appointment.name.slice(0, 42)}
              </span>
              <span class="name-tag">
                {props.status === "As Buyer" ? "OWNER" : "TENANT"}
              </span>
            </div>
          </li>
          <li class="appointment-item">
            <div class="grid grid--3-cols">
              <div class="attribute-tag">
                <span class="appointment-days">
                  {props.appointment.appoint_day}
                </span>
                <span class="day-tag">DAY</span>
              </div>
              <div class="attribute-tag">
                <span class="appointment-phone">
                  {props.appointment.phone_no}
                </span>
                <span class="phone-tag">PHONE</span>
              </div>
              <div class="attribute-tag">
                <span class="appointment-email">
                  {props.appointment.email_id.length < 17
                    ? props.appointment.email_id
                    : props.appointment.email_id.slice(0, 17) + ".."}
                </span>
                <span class="email-tag">EMAIL</span>
              </div>
            </div>
          </li>
          <li class="appointment-item">
            <div class="appointment-desc">
              {props.appointment.house_description}
            </div>
          </li>
          <li class="appointment-item">
            <div class="grid grid--3-cols">
              <div class="attribute-tag">
                <span class="appointment-street">
                  {props.appointment.street.length < 17
                    ? props.appointment.street
                    : props.appointment.street.slice(0, 17) + ".."}
                </span>
                <span class="street-tag">STREET</span>
              </div>
              <div class="attribute-tag">
                <span class="appointment-city">{props.appointment.city}</span>
                <span class="city-tag">CITY</span>
              </div>
              <div class="attribute-tag">
                <span class="appointment-state">{props.appointment.state}</span>
                <span class="state-tag">STATE</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Appointment_2;
