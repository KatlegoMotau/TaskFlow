import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

function Navbar() {

    const { logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/");
    };

    return (
        <header className="navbar">
            <h3>TaskFlow Dashboard</h3>

            <button onClick={handleLogout}>
                Logout
            </button>
        </header>
    );
}

export default Navbar;