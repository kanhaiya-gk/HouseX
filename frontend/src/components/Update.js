import React, { useState, useEffect } from "react";

const Update = () => {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("********************");

  const initializeProfile = () => {
    fetch(`${process.env.REACT_APP_URL}/myprofile`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setName(data.name);
        setPhone(data.phone_no);
        setEmail(data.email_id);
      });
  };

  useEffect(() => {
    initializeProfile();
  }, []);

  const handleName = (e) => {
    setName(e.target.value);
  };

  const handlePhone = (e) => {
    setPhone(e.target.value);
  };

  const handlePassword = (e) => {
    if (password === "********************") {
      setPassword("");
    } else {
      setPassword(e.target.value);
    }
  };

  const handleUpdateProfile = (e) => {
    e.preventDefault();
    const update_profile_data = {
      name: name,
      phone_no: phone,
    };

    if (password !== "********************") {
      update_profile_data["password"] = password;
    }

    fetch(`${process.env.REACT_APP_URL}/myprofile/update`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(update_profile_data),
    })
      .then((response) => response.json())
      .then((data) => {
        window.location.href = `${process.env.REACT_APP_URL}`;
      });
  };

  return (
    <section class="section-update">
      <div class="conatiner center-text">
        <span class="subheading margin--sm">profile</span>
        {/* <h2 class="heading-secondary bookmark-heading">Update your profile</h2> */}
      </div>
      <ul class="update-list">
        <form action="#" onSubmit={handleUpdateProfile}>
          <li class="update-item">
            <label for="name" class="update-label">
              Name
            </label>
            <div class="update-icon-input">
              <svg
                class="update-icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z"
                />
              </svg>

              <input
                onChange={handleName}
                type="text"
                class="update-name"
                placeholder="Enter your new name"
                value={name}
              />
            </div>
          </li>

          <li class="update-item">
            <label for="phone" class="update-label">
              Phone
            </label>

            <div class="update-icon-input">
              <svg
                class="update-icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 002.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 01-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 00-1.091-.852H4.5A2.25 2.25 0 002.25 4.5v2.25z"
                />
              </svg>

              <input
                onChange={handlePhone}
                type="text"
                class="update-phone"
                placeholder="Enter your new phone"
                value={phone}
              />
            </div>
          </li>

          <li class="update-item">
            <label for="email" class="update-label">
              Email
            </label>

            <div class="update-icon-input">
              <svg
                class="update-icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75"
                />
              </svg>

              <input
                disabled="disabled"
                type="text"
                class="update-email"
                placeholder="zaidmanglia@gmail.com"
                value={email}
              />
            </div>
          </li>
          <li class="update-item">
            <label for="password" class="update-label">
              Password
            </label>

            <div class="update-icon-input">
              <svg
                class="update-icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M15.75 5.25a3 3 0 013 3m3 0a6 6 0 01-7.029 5.912c-.563-.097-1.159.026-1.563.43L10.5 17.25H8.25v2.25H6v2.25H2.25v-2.818c0-.597.237-1.17.659-1.591l6.499-6.499c.404-.404.527-1 .43-1.563A6 6 0 1121.75 8.25z"
                />
              </svg>

              <input
                onChange={handlePassword}
                type="password"
                class="update-password"
                placeholder="Enter your new password"
                value={password}
              />
            </div>
          </li>
          <li class="update-item">
            <button class="update-button">Update</button>
          </li>
        </form>
      </ul>
    </section>
  );
};

export default Update;
