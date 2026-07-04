import api from "./api";

export async function getDashboard() {

    const response = await api.get("/tasks/dashboard");

    return response.data;

}