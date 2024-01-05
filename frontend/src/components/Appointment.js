import React, { useEffect, useState } from "react";
import Appointment_2 from "./Appointment_2";
import Appointment_tag from "./Appointment_tag";

const Appointment = () => {
  const [appointments, setAppointments] = useState([]);
  const [status, setStatus] = useState("As Buyer");

  const deleteAppointmentFromChild = (appointment_id) => {
    const tempAppointments = appointments.filter(
      (appointment) => appointment.house_id !== appointment_id
    );
    setAppointments(tempAppointments);
  };

  const initializeAppointments = () => {
    fetch(`${process.env.REACT_APP_URL}/appointments/buyer`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (Array.isArray(data)) {
          setAppointments(data);
        }
      });
  };

  const handleTagAppointments = (appointmentss) => {
    setAppointments(appointmentss);
  };

  const handleStatus = (statuss) => {
    setStatus(statuss);
  };

  useEffect(() => {
    initializeAppointments();
  }, []);

  return (
    <section class="section-appointments">
      <Appointment_tag
        handleTagAppointments={handleTagAppointments}
        handleStatus={handleStatus}
        status={status}
      />
      <div class="conatiner center-text">
        <span class="subheading">APPOINTMENTS</span>
        <h2 class="heading-secondary margin--md">
          Record and manage your appointments
        </h2>
      </div>
      <ul class="container grid grid--1-cols">
        {appointments.map((appointment) => {
          return (
            <Appointment_2
              key={appointment.house_id}
              appointment={appointment}
              deleteAppointmentFromChild={deleteAppointmentFromChild}
              appointment_id={appointment.house_id}
              status={status}
            />
          );
        })}
      </ul>
    </section>
  );
};

export default Appointment;
