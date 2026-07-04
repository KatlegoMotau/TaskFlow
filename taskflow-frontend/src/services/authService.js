import api from "./api";

export const login = async (credentials) => {

    const response = await api.post("/auth/login", credentials);

    return response.data;
};

export const register = async (user) => {

    const response = await api.post("/auth/register", user);

    return response.data;
};