import React, { useEffect } from "react";
import image_collage from "../static/image_collage.jpeg";

const Home = () => {
  // For Development
  useEffect(() => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const token = urlParams.get("token");
    if (token !== null) {
      localStorage.setItem("token", token);
    }
  }, []);

  return (
    <>
      <section class="section-hero">
        <div class="hero">
          <div class="hero-text-box">
            <h1 class="heading-primary">Find your dream home with ease</h1>
            <p class="hero-description">
              Lease a house or rent a house HouseX.com is where you should be.
              All property needs one portal. Find better place to live work and
              wonder.
            </p>
            <a class="btn btn--full" href='/search' style={{ 'text-decoration': 'none' }}>Search houses</a>
          </div>
          <div class="hero-img-box">
            <picture>
              <img src={image_collage} alt="Houses Image" class="hero-img" />
            </picture>
          </div>
        </div>
      </section>

      <section class="section-features">
        <div class="container grid grid--4-cols">
          <div class="feature">
            <div class="feature-icon">
              <svg
                class="icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z"
                />
              </svg>
            </div>
            <p class="feature-title">Rated and Reviewed</p>
            <p class="feature-text">
              Testimonials from geniune buyers are the best ways to judge.
              Better the ratings better will be your experience
            </p>
          </div>

          <div class="feature">
            <div class="feature-icon">
              <svg
                class="icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M22 10.5h-6m-2.25-4.125a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zM4 19.235v-.11a6.375 6.375 0 0112.75 0v.109A12.318 12.318 0 0110.374 21c-2.331 0-4.512-.645-6.374-1.766z"
                />
              </svg>
            </div>
            <p class="feature-title">No Brokerage</p>
            <p class="feature-text">
              We directly connect you to verified owners to save brokerage
            </p>
          </div>

          <div class="feature">
            <div class="feature-icon">
              <svg
                class="icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
                />
              </svg>
            </div>
            <p class="feature-title">Rental Agreement</p>
            <p class="feature-text">
              Assistance in creating rental agreement and paper work, makes it
              easier to keep track of transactions and manage them
            </p>
          </div>

          <div class="feature">
            <div class="feature-icon">
              <svg
                class="icon w-6 h-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
                />
              </svg>
            </div>
            <p class="feature-title">Shortlist without visit</p>
            <p class="feature-text">
              We can search and bookmark our favorite properties. Extensive
              information makes it easy
            </p>
          </div>
        </div>
      </section>
    </>
  );
};

export default Home;
