// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
  const facts =
      ['My favorite color is blue. :)', 'I\'m a big coffee person.', 'I am of Mexican descent.', 'My top genres on Spotify are rock, indie, rap, and Latin!'];

  // Pick a random fact.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

async function addRandomSong() {
  const responseFromServer = await fetch('/songs');
  const textFromResponse = await responseFromServer.json();

  const song = textFromResponse;

  const songContainer = document.getElementById('song-container');
  songContainer.innerText = song;
}

async function loadContactInfo() {

  const responseFromServer = await fetch('/confirm');
  const textFromResponse = await responseFromServer.json();
  const contactContainer = document.getElementById('contact-container');

  contactContainer.innerText = "Last submission from: " + textFromResponse;
}
