<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String urlPokemon = request.getParameter("id");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="newSession.jsp">View all Pokemons</a>
	<div>
		<h2 id="pkname"></h2>
		<ul id="pokemons"></ul>
	</div>
	<div id="image"></div>
	<script>
		const pokemons = document.getElementById("pokemons");
		const name = document.getElementById("pkname");
		const image = document.getElementById("image");
		const getPokemon = async () => {
			try {
				const response = await fetch("<%=urlPokemon%>");
				if (response.status === 200) {
					const data = await response.json();
					data.abilities.forEach((element) => {
						const li = document.createElement('li');
						li.appendChild(document.createTextNode(element.ability.name))
						pokemons.appendChild(li);
					})
					name.appendChild(document.createTextNode(data.name));
					const img = document.createElement("img");
					img.src = data.sprites.front_default;
					image.appendChild(img);
				}
			} catch (err) {
				console.log(err)
			}	
		}
		getPokemon(); 
	</script>
</body>
</html>