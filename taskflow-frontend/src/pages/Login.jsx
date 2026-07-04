/* eslint-disable no-unused-vars */
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { login as loginRequest } from "../services/authService";
import { useAuth } from "../hooks/useAuth";

function Login() {

    const navigate = useNavigate();

    const { login } = useAuth();

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const [error, setError] = useState("");

    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setError("");

        setLoading(true);

        try {

            const response = await loginRequest({
                email,
                password
            });

            login(response.token);

            navigate("/dashboard");

        } catch (err) {

            setError("Invalid email or password.");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="login-container">

            <form
                className="login-card"
                onSubmit={handleSubmit}
            >

                <h1>TaskFlow</h1>

                <p>Sign in to continue</p>

                {error && (

                    <div className="error">

                        {error}

                    </div>

                )}

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) =>
                        setEmail(e.target.value)
                    }
                    required
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) =>
                        setPassword(e.target.value)
                    }
                    required
                />

                <button
                    type="submit"
                    disabled={loading}
                >

                    {loading ? "Signing In..." : "Login"}

                </button>

                <p>

                    Don't have an account?{" "}

                    <Link to="/register">

                        Register

                    </Link>

                </p>

            </form>

        </div>

    );

}

export default Login;