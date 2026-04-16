import React, { useEffect, useState } from "react";

const API_BASE = "http://54.166.129.233:8080";

function App() {
  const [flights, setFlights] = useState([]);
  const [airports, setAirports] = useState([]);
  const [airlines, setAirlines] = useState([]);
  const [gates, setGates] = useState([]);

  const [flightNumber, setFlightNumber] = useState("");
  const [status, setStatus] = useState("ON_TIME");

  const [selectedAirport, setSelectedAirport] = useState("");
  const [selectedAirline, setSelectedAirline] = useState("");
  const [selectedGate, setSelectedGate] = useState("");

  const [departureTime, setDepartureTime] = useState("");
  const [arrivalTime, setArrivalTime] = useState("");

  const [filterAirport, setFilterAirport] = useState("");

  useEffect(() => {
    fetchFlights();
    fetchAirports();
    fetchAirlines();
    fetchGates();
  }, []);

  const fetchFlights = async () => {
    const res = await fetch(`${API_BASE}/flights`);
    const data = await res.json();
    setFlights(data);
  };

  const fetchAirports = async () => {
    const res = await fetch(`${API_BASE}/airports`);
    const data = await res.json();
    setAirports(data);
  };

  const fetchAirlines = async () => {
    const res = await fetch(`${API_BASE}/airlines`);
    const data = await res.json();
    setAirlines(data);
  };

  const fetchGates = async () => {
    const res = await fetch(`${API_BASE}/gates`);
    const data = await res.json();
    setGates(data);
  };

  const addFlight = async () => {
    if (!flightNumber || !selectedAirport || !selectedAirline) {
      alert("Please fill required fields");
      return;
    }

    await fetch(`${API_BASE}/flights`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        flightNumber,
        status,
        departureTime: departureTime || null,
        arrivalTime: arrivalTime || null,
        airport: { id: Number(selectedAirport) },
        airline: { id: Number(selectedAirline) },
        gate: selectedGate ? { id: Number(selectedGate) } : null,
      }),
    });

    setFlightNumber("");
    setStatus("ON_TIME");
    setSelectedAirport("");
    setSelectedAirline("");
    setSelectedGate("");
    setDepartureTime("");
    setArrivalTime("");

    fetchFlights();
  };

  const deleteFlight = async (id) => {
    await fetch(`${API_BASE}/flights/${id}`, { method: "DELETE" });
    fetchFlights();
  };

  const filteredFlights = filterAirport
    ? flights.filter(
        (f) => f.airport && f.airport.id === Number(filterAirport)
      )
    : flights;

  return (
    <div style={{
      background: "#f1f5f9",
      minHeight: "100vh",
      padding: "30px",
      fontFamily: "Arial"
    }}>
      <h1 style={{ fontSize: "32px", marginBottom: "20px" }}>
        ✈️ Aviation Dashboard
      </h1>

      <select value={filterAirport} onChange={(e) => setFilterAirport(e.target.value)}>
        <option value="">All Airports</option>
        {airports.map((a) => (
          <option key={a.id} value={a.id}>{a.name}</option>
        ))}
      </select>

      <h2>Add Flight</h2>

      <input placeholder="Flight Number" value={flightNumber} onChange={(e) => setFlightNumber(e.target.value)} />

      <select value={status} onChange={(e) => setStatus(e.target.value)}>
        <option value="ON_TIME">ON_TIME</option>
        <option value="DELAYED">DELAYED</option>
        <option value="CANCELLED">CANCELLED</option>
      </select>

      <select value={selectedAirport} onChange={(e) => setSelectedAirport(e.target.value)}>
        <option value="">Select Airport</option>
        {airports.map((a) => (
          <option key={a.id} value={a.id}>{a.name}</option>
        ))}
      </select>

      <select value={selectedAirline} onChange={(e) => setSelectedAirline(e.target.value)}>
        <option value="">Select Airline</option>
        {airlines.map((a) => (
          <option key={a.id} value={a.id}>{a.name}</option>
        ))}
      </select>

      <select value={selectedGate} onChange={(e) => setSelectedGate(e.target.value)}>
        <option value="">Select Gate</option>
        {gates.map((g) => (
          <option key={g.id} value={g.id}>{g.gateNumber}</option>
        ))}
      </select>

      <br /><br />

      <input type="datetime-local" value={departureTime} onChange={(e) => setDepartureTime(e.target.value)} />
      <input type="datetime-local" value={arrivalTime} onChange={(e) => setArrivalTime(e.target.value)} />

      <br /><br />

      <button onClick={addFlight}>Add Flight</button>

      <hr />

      {filteredFlights.map((f) => (
        <div key={f.id} style={{
          borderRadius: "12px",
          padding: "15px",
          marginBottom: "15px",
          background: "#fff",
          boxShadow: "0 4px 10px rgba(0,0,0,0.1)"
        }}>
          <h3>✈️ {f.flightNumber}</h3>

          <p style={{
            fontWeight: "bold",
            color:
              f.status === "ON_TIME"
                ? "green"
                : f.status === "DELAYED"
                ? "orange"
                : "red"
          }}>
            Status: {f.status}
          </p>

          <p>🏢 {f.airport?.name}</p>
          <p>🛫 {f.airline?.name}</p>
          <p>🚪 {f.gate?.gateNumber}</p>

          <p>⏰ {f.departureTime ? new Date(f.departureTime).toLocaleString() : ""}</p>
          <p>🛬 {f.arrivalTime ? new Date(f.arrivalTime).toLocaleString() : ""}</p>

          <button
            onClick={() => deleteFlight(f.id)}
            style={{
              marginTop: "10px",
              padding: "8px 12px",
              background: "#ef4444",
              color: "white",
              border: "none",
              borderRadius: "6px"
            }}
          >
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}

export default App;