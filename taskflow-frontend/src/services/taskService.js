import api from "./api";

// Get all tasks
export const getTasks = async () => {
    const response = await api.get("/tasks");
    return response.data;
};

// Create a task
export const createTask = async (task) => {
    const response = await api.post("/tasks", task);
    return response.data;
};

// Update a task
export const updateTask = async (id, task) => {
    const response = await api.put(`/tasks/${id}`, task);
    return response.data;
};

// Delete a task
export const deleteTask = async (id) => {
    await api.delete(`/tasks/${id}`);
};

// Dashboard
export const getDashboard = async () => {
    const response = await api.get("/tasks/dashboard");
    return response.data;
};

// Search
export const searchTasks = async (title) => {
    const response = await api.get(`/tasks/search?title=${title}`);
    return response.data;
};

// Filter by status
export const getTasksByStatus = async (status) => {
    const response = await api.get(`/tasks/status/${status}`);
    return response.data;
};

// Filter by priority
export const getTasksByPriority = async (priority) => {
    const response = await api.get(`/tasks/priority/${priority}`);
    return response.data;
};

//For deleting and updating
export const getTaskById = async (id) => {

    const response = await api.get(`/tasks/${id}`);

    return response.data;

};

//For downloading as CSV
export const exportCSV = async () => {

    const response = await api.get("/tasks/export/csv", {

        responseType: "blob"

    });

    return response.data;

};

//For downloading as PDF
export const exportPDF = async () => {

    const response = await api.get("/tasks/export/pdf", {

        responseType: "blob"

    });

    return response.data;

};