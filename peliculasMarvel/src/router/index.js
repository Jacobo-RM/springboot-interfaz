import { createRouter, createWebHistory } from 'vue-router';
import ListaPeliculas from '../components/ListaPeliculas.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: ListaPeliculas
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;