import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { useFetchQueue, pushToQueue, popFromQueue, fetchNewId } from './services/fetchHandler';

function App() {
  const {songQueue, users} = useFetchQueue();
  const [update, setUpdate] = useState(0);
  const [newSong, setNewSong] = useState('');

  //Could also store user info locally to test if they are already in a session
  //const [name, setName] = useState(() => localStorage.getItem('name')); 
  //const [email, setEmail] = useState(() => localStorage.getItem('email'));

  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [userID, setUserID] = useState(() => localStorage.getItem('userID')); // Initialize from localStorage
  const [hasPremium, setPremium] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false); //Used for conditional rendering of the login form

  useEffect(() => {
    async function getUserID() {
      if (!userID) { // Only fetch a new ID if it doesn't exist in localStorage
        try {
          const id = await fetchNewId(name, email, hasPremium); //Send user data and request an ID given from the backend
          setUserID(id); // Set the result in state
          localStorage.setItem('userID', id); // Save the ID in localStorage
        } catch (error) {
          console.error('Failed to fetch user ID:', error);
        }
      }
    }

    if (isLoggedIn) {
      getUserID();
    }
  }, [isLoggedIn, userID]); // Run if the user logs in and does not have a userID

  const handlePush = async () => {
    try {
      const response = await pushToQueue({ name: newSong }, userID); // Push the new song data
      setNewSong(''); // Clear input field after submission
    } catch (error) {
      console.error('Error pushing song:', error);
    }
  };
  
  const handlePop = async () => {
    try {
      const response = await popFromQueue();
    } catch (error) {
      console.error('Error pushing song:', error);
    }
  };

  const handleLogin = () => {
    if (name && email) {
      setIsLoggedIn(true); 
    } else {
      alert("Please enter both a name and an email.");
    }
  };
  
  const handleLoginPremium = () => {
    if (name && email) {
      setIsLoggedIn(true); 
	  setPremium(true);
    } else {
      alert("Please enter both a name and an email.");
    }
  };
  
  const clearId = () => {
	localStorage.removeItem('userID');
	setUserID(null);
	setIsLoggedIn(false);
  };
  
  return (
    <div className="App">
      {(!isLoggedIn&&!userID) ? (
        // Login Form
        <div>
          <h2>Enter your details to access the queue</h2>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter your name"
          />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter your email"
          />
          <button onClick={handleLogin}>Basic Account</button>
		  <button onClick={handleLoginPremium}>Premium Account</button>
        </div>
      ) : (
        
		
		// Main Content
        <div>
          <h1>Song Queue</h1>
		  <ul>
			{songQueue.map((song, index) => (
			  <li key={index}>
				{song.name} - {song.genre} ({song.duration} mins)
			  </li>
			))}
		  </ul>

          <input
            type="text"
            value={newSong}
            onChange={(e) => setNewSong(e.target.value)}
            placeholder="Enter a new song name"
          />
          <button onClick={handlePush}>Add to Queue</button>
          <button onClick={handlePop}>Pop Queue</button>
          
		  <p>User ID: {userID}</p>
		  <button onClick={clearId}>Clear ID</button>
		  
		  <h3>Current Users:</h3>
		  <ul>
			{users.map((user, index) => (
			  <li key={index}>
				{user.username} - {user.email} {user.hasPremium ? "(Premium)" : ""}
			  </li>
			))}
		  </ul>
        </div>
      )}
    </div>
  );
}

export default App;
