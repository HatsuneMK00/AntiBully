import Vue from 'vue'
import VueRouter from 'vue-router'
import CourseManagement from "@/components/CourseManagement";
import ExerciseManagement from "@/components/ExerciseManagement";

Vue.use(VueRouter);

const routes = [
    {
        path: '/course',
        name: 'course',
        component: CourseManagement
    },
    {
        path: '/',
        name: 'home',
        redirect: '/course'
    },
    {
        path: '/exercise',
        name: 'exercise',
        component: ExerciseManagement
    }

];

const router = new VueRouter({
    routes
});

export default router

