import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register as registerRequest } from "../services/authService";

function Register() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    });

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    const [success, setSuccess] = useState("");

    const handleChange = (e) => {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        setLoading(true);
        setError("");
        setSuccess("");

        try {

            await registerRequest(form);

            setSuccess("Registration successful! Redirecting to login...");

            setTimeout(() => {

                navigate("/");

            }, 2000);

        } catch (err) {

            setError(
                err.response?.data?.message ||
                "Registration failed."
            );

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

                <p>Create your account</p>

                {error && (

                    <div className="error">

                        {error}

                    </div>

                )}

                {success && (

                    <div className="success">

                        {success}

                    </div>

                )}

                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={form.firstName}
                    onChange={handleChange}
                    required
                />

                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={form.lastName}
                    onChange={handleChange}
                    required
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={form.email}
                    onChange={handleChange}
                    required
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={form.password}
                    onChange={handleChange}
                    required
                />

                <button
                    type="submit"
                    disabled={loading}
                >

                    {loading
                        ? "Creating Account..."
                        : "Register"}

                </button>

                <p>

                    Already have an account?{" "}

                    <Link
                        to="/"
                        style={{
                            color: "#2563eb",
                            fontWeight: "bold"
                        }}
                    >
                        Login
                    </Link>

                </p>

            </form>

        </div>

    );

}

export default Register;