import { useEffect, useState } from 'react';

const baseUrl = 'http://localhost:8080'

/*
push - add to queue
pop - remove top
remove/{index} - remove X song
size - get size
*/


export function useFetchQueue() {
  const [songQueue, setSongQueue] = useState([]);
  const [users, setUsers] = useState([]);
  
  useEffect(() => {
    const fetchData = () => {
    //Request song queue  
	  fetch(baseUrl+'/session/queue')
        .then((response) => response.json()) // Adjust based on expected data format
        .then((data) => setSongQueue(data))
        .catch((error) => console.error('Error fetching data:', error));
    
	//Request list of users
      fetch(baseUrl + '/session/users')
        .then((response) => response.json()) // Assuming the users endpoint returns JSON
        .then((data) => setUsers(data))
        .catch((error) => console.error('Error fetching users:', error));
    
	
	};

    fetchData(); // Initial fetch
    const interval = setInterval(fetchData, 5000); // Fetch every 5 seconds

    return () => clearInterval(interval); // Cleanup interval on component unmount
  }, []); // Empty dependency array to set up only once on mount

  return {songQueue, users }; // Return the data so that it can be used in App.js
}

export async function pushToQueue(newData, userID) {
  const songName = newData.name;
  try {
    const response = await fetch(`${baseUrl}/session/push?id=${userID}`, {
	  method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
	  body: JSON.stringify({
        name: songName,
        genre: 'PushedSong',
        duration: userID,
      }),
	  
	});

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const result = await response.json(); // Adjust based on response format
    return result; // Return the response data, if needed
  } catch (error) {
    console.error('Error pushing to queue:', error);
    throw error; // Re-throw the error for handling in App.js if necessary
  }
  
}

export async function popFromQueue() {
  try {
    const response = await fetch(baseUrl+'/session/pop', {
      method: 'DELETE',
	})

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const result = await response.json(); // Adjust based on response format
    return result; // Return the response data, if needed
  } catch (error) {
    console.error('Error pushing to queue:', error);
    throw error; // Re-throw the error for handling in App.js if necessary
  }
  
}

export async function fetchNewId(name, email, hasPremium) {
  try {
    const response = await fetch(`${baseUrl}/session/newid?name=${name}&email=${email}&hasPremium=${hasPremium}`, {
      method: 'POST',
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const result = await response.text(); // Read the response as plain text
    return parseInt(result, 10); // Convert the result to an integer
  } catch (error) {
    console.error('Error fetching new user ID:', error);
    throw error; // Re-throw the error for handling in App.js if necessary
  }
}

