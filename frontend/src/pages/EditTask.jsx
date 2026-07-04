/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import {
    getTaskById,
    updateTask
} from "../services/taskService";

function EditTask() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [task, setTask] = useState({

        title: "",
        description: "",
        priority: "MEDIUM",
        status: "TODO",
        dueDate: ""

    });

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadTask();

    }, []);

    const loadTask = async () => {

        try {

            const data = await getTaskById(id);

            setTask(data);

        } catch (error) {

            console.error(error);

            alert("Failed to load task.");

        } finally {

            setLoading(false);

        }

    };

    const handleChange = (e) => {

        setTask({

            ...task,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await updateTask(id, task);

            alert("Task updated successfully!");

            navigate("/tasks");

        } catch (error) {

            console.error(error);

            alert("Failed to update task.");

        }

    };

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <div>

            <h1>Edit Task</h1>

            <form className="task-form" onSubmit={handleSubmit}>

                <label>Title</label>

                <input
                    type="text"
                    name="title"
                    value={task.title}
                    onChange={handleChange}
                    required
                />

                <label>Description</label>

                <textarea
                    name="description"
                    value={task.description}
                    onChange={handleChange}
                    rows="5"
                    required
                />

                <label>Priority</label>

                <select
                    name="priority"
                    value={task.priority}
                    onChange={handleChange}
                >
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                </select>

                <label>Status</label>

                <select
                    name="status"
                    value={task.status}
                    onChange={handleChange}
                >
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                </select>

                <label>Due Date</label>

                <input
                    type="date"
                    name="dueDate"
                    value={task.dueDate}
                    onChange={handleChange}
                    required
                />

                <button type="submit">

                    Update Task

                </button>

            </form>

        </div>

    );

}

export default EditTask;