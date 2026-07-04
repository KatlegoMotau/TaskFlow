/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getTasks, deleteTask } from "../services/taskService";

function Tasks() {

    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {

        loadTasks();

    }, []);

    async function loadTasks() {

        try {

            const response = await getTasks();

            setTasks(response.content);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    }

    const handleDelete = async (id) => {

    const confirmed = window.confirm(
        "Are you sure you want to delete this task?"
        );

        if (!confirmed) return;

        try {

            await deleteTask(id);

            loadTasks();

        } catch (error) {

            console.error(error);

            alert("Failed to delete task.");

        }

    };

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <div>

            <h1>My Tasks</h1>

            <table className="task-table">

                <thead>

                    <tr>

                        <th>Title</th>
                        <th>Status</th>
                        <th>Priority</th>
                        <th>Due Date</th>
                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {tasks.length === 0 ? (

                        <tr>

                            <td colSpan="5">
                                No Tasks Found
                            </td>

                        </tr>

                    ) : (

                        tasks.map(task => (

                            <tr key={task.id}>

                                <td>{task.title}</td>

                                <td>{task.status}</td>

                                <td>{task.priority}</td>

                                <td>{task.dueDate}</td>

                                <td>

                                    <button
                                        onClick={() => navigate(`/edit-task/${task.id}`)}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        onClick={() => handleDelete(task.id)}
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))

                    )}

                </tbody>

            </table>

        </div>

    );

}

export default Tasks;