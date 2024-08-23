<template>
    <div class="container">
      <h1>Películas</h1>
      <button @click="mostrarPeliculas" class="btn">Cargar Películas</button>
      <div v-if="error" class="error">
        Error: {{ error }}
      </div>
      <div>
        <select v-model="peliculaNombre" @change="filtrarPeliculaPorNombre" class="select">
          <option value="">Seleccione una película</option>
          <option v-for="pelicula in peliculas" :key="pelicula.id" :value="pelicula.nombre">
            {{ pelicula.nombre }}
          </option>
        </select>
      </div>
      <ul v-if="mostrarLista" class="peliculas-list">
        <li v-for="pelicula in peliculasFiltradas" :key="pelicula.id" class="pelicula-item">
          {{ pelicula.nombre }} - {{ pelicula.fechaEstreno }} - {{ pelicula.rating }}
        </li>
      </ul>
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
  
  const peliculasFiltradas = computed(() => {
    if (mostrarTodas.value) {
      return peliculas.value;
    }
    return peliculas.value.filter(pelicula => pelicula.nombre === peliculaNombre.value);
  });
  
  const fetchPeliculas = async () => {
    try {
      const response = await axios.get('/api/cine/peliculas');
      peliculas.value = response.data;
    } catch (err) {
      console.error('Error fetching peliculas:', err);
      error.value = err.message;
    }
  };
  
  const filtrarPeliculaPorNombre = () => {
    mostrarTodas.value = false;
    mostrarLista.value = true;
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
  
  .btn {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    margin: 10px 0;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
  }
  
  .btn:hover {
    background-color: #0056b3;
  }
  
  .select {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
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