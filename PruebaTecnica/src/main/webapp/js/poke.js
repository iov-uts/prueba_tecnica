const pokeDiv = document.getElementById("pokediv");
const URL = "https://pokeapi.co/api/v2/pokemon/";

const getPokemons = async () => {
	try {
		const response = await fetch(URL);
		if (response.status === 200) {
			const data = await response.json();
			let pokemons = "";
			data.results.forEach( pokemon => {		
				pokemons += `<div  class="pokemon"><span id=${pokemon.url} class="pk-name">${pokemon.name}</span></div>`;
			});
			pokeDiv.innerHTML = pokemons;
		}
	} catch (err) {
		console.log(err)
	}	
}

getPokemons(); 


pokeDiv.addEventListener('click', (e) => {
	if ( e.target.className == "pk-name") {
		window.location.assign(`home?go=pokemon&id=${e.target.id}`);
	}
})


