/* eslint-disable no-unused-vars */
import { Link } from "react-router-dom";
import { NavLink } from "react-router-dom";

function Sidebar() {

    return (

        <aside className="sidebar">

            <h2>TaskFlow</h2>

            <ul>

                <li>

                    <NavLink to="/dashboard">

                        📊 Dashboard

                    </NavLink>

                </li>

                <li>

                    <NavLink to="/tasks">

                        📋 Tasks

                    </NavLink>

                </li>

                <li>

                    <NavLink to="/create-task">

                        ➕ New Task

                    </NavLink>

                </li>

                <li>

                    <NavLink to="/export">

                        📄 Export

                    </NavLink>

                </li>

            </ul>

        </aside>

    );

}

export default Sidebar;