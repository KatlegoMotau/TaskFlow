import { useState } from "react";
import { AuthContext } from "./AuthContext";

export function AuthProvider({ children }) {

    const [token, setToken] = useState(localStorage.getItem("token"));

    const login = (jwt) => {
        localStorage.setItem("token", jwt);
        setToken(jwt);
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
    };

    return (
        <AuthContext.Provider
            value={{
                token,
                login,
                logout,
                isAuthenticated: !!token
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}
