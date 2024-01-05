import "./App.css";
import Upload from "./components/Upload";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import Update from "./components/Update";
import Appointment from "./components/Appointment";
import Search from "./components/Search";

import { Route, Routes, Link } from "react-router-dom";
import Search_3 from "./components/Search_3";

function App() {
  const NotFound = () => {
    return (window.location.href = `${process.env.REACT_APP_URL}`)
  };

  const Main = () => {
    return (
      <>
        <Navbar />
        <Routes>
          <Route path="/home/*" element={<Home />} />
          <Route path="/search" element={<Search />} />
          <Route path="/house_info/*" element={<Search_3 />} />
          <Route path="/appointment" element={<Appointment />} />
          <Route path="/upload" element={<Upload />} />
          <Route path="/update" element={<Update />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </>
    );
  };

  return (
    <div className="App">
      <Routes>
        <Route path="*" element={<Main />} />
      </Routes>
    </div>
  );
}

export default App;
