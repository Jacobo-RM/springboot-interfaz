<template>
  <div class="container">
    <h1>Películas</h1>
    <button @click="mostrarPeliculas" class="btn">Cargar Películas</button>
    
    <form @submit.prevent="anadirPelicula" class="form">
      <h2>Añadir Película</h2>
      <input v-model="nuevaPelicula.nombre" type="text" placeholder="Nombre de la Película" required />
      <input v-model="nuevaPelicula.fechaEstreno" type="date" placeholder="Fecha de Estreno" required />
      <input v-model.number="nuevaPelicula.rating" type="number" step="0.1" placeholder="Rating" required />
      <button type="submit" class="btn">Añadir Película</button>
    </form>
    
    <div v-if="error" class="error">
      Error: {{ error }}
    </div>
    
    <ul v-if="mostrarLista" class="peliculas-list">
      <li v-for="pelicula in peliculasFiltradas" :key="pelicula.id" class="pelicula-item">
        {{ pelicula.nombre }} - {{ pelicula.fechaEstreno }} - {{ pelicula.rating }}
        <button @click="eliminarPelicula(pelicula.id)">Eliminar</button>
        <button @click="iniciarEdicion(pelicula.id, pelicula.nombre)">Editar</button>
      </li>
    </ul>
    
    <div v-if="editando" class="edit-form">
      <h2>Editar Película</h2>
      <form @submit.prevent="actualizarPelicula">
        <input v-model="peliculaEditada.nombre" type="text" placeholder="Nuevo nombre de la película" required />
        <button type="submit">Actualizar Película</button>
        <button @click="cancelarEdicion">Cancelar</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';

const peliculas = ref([]);
const error = ref(null);
const peliculaNombre = ref('');
const mostrarTodas = ref(true);
const mostrarLista = ref(false);
const nuevaPelicula = ref({
  nombre: '',
  fechaEstreno: '',
  rating: 0
});

const editando = ref(false);
const peliculaEditada = ref({
  id: null,
  nombre: ''
});

const peliculasFiltradas = computed(() => {
  if (mostrarTodas.value) {
    return peliculas.value;
  }
  return peliculas.value.filter(pelicula => pelicula.nombre === peliculaNombre.value);
});

const fetchPeliculas = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/cine/peliculas');
    peliculas.value = response.data;
  } catch (err) {
    console.error('Error fetching peliculas:', err);
    error.value = err.message;
  }
};

const anadirPelicula = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/cine/anadirPelicula', nuevaPelicula.value);
    peliculas.value.push(response.data);
    nuevaPelicula.value = { nombre: '', fechaEstreno: '', rating: 0 };
    error.value = null;
  } catch (err) {
    console.error('Error añadiendo película:', err);
    error.value = err.message;
  }
};

const eliminarPelicula = async (id) => {
  try {
    await axios.delete(`http://localhost:8080/api/cine/borrarPelicula/${id}`);
    peliculas.value = peliculas.value.filter(pelicula => pelicula.id !== id);
    error.value = null;
  } catch (err) {
    console.error('Error eliminando película:', err);
    error.value = err.message;
  }
};

const iniciarEdicion = (id, nombre) => {
  peliculaEditada.value.id = id;
  peliculaEditada.value.nombre = nombre;
  editando.value = true;
};

const actualizarPelicula = async () => {
  try {
    const { id, nombre } = peliculaEditada.value;
    const response = await axios.put(`http://localhost:8080/api/cine/actualizarPelicula/${id}/${nombre}`);
    const index = peliculas.value.findIndex(pelicula => pelicula.id === id);
    peliculas.value[index] = response.data;
    cancelarEdicion();
  } catch (err) {
    console.error('Error actualizando película:', err);
    error.value = err.message;
  }
};

const cancelarEdicion = () => {
  editando.value = false;
  peliculaEditada.value = { id: null, nombre: '' };
};

const mostrarPeliculas = () => {
  mostrarTodas.value = true;
  peliculaNombre.value = '';
  mostrarLista.value = true;
};

onMounted(() => {
  fetchPeliculas();
});
</script>


  
 <style scoped>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
  }

  .form {
    margin: 20px 0;
    text-align: left;
  }
  
  .form input {
    width: calc(100% - 22px);
    padding: 10px;
    margin: 5px 0;
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 16px;
  }
  
  .peliculas-list {
    list-style-type: none;
    padding: 0;
  }
  
  .pelicula-item {
    background-color: #f8f9fa;
    margin: 10px 0;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
  
  .hidden {
    display: none;
  }
  
  .error {
    color: red;
    margin: 10px 0;
  }
  </style>
