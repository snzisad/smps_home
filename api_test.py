// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBO24huTNrIgs-eMPCyAVKMs5qAj7BkGC8",
  authDomain: "smps-home.firebaseapp.com",
  projectId: "smps-home",
  storageBucket: "smps-home.appspot.com",
  messagingSenderId: "324485548588",
  appId: "1:324485548588:web:c3188a088a37c644ec9f89",
  measurementId: "G-WMNE5RGHPD"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);