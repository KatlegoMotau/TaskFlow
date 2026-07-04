/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";

import DashboardCard from "../components/DashboardCard";
import { getDashboard } from "../services/dashboardService";

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadDashboard();

    }, []);

    async function loadDashboard() {

        try {

            const data = await getDashboard();

            setDashboard(data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    }

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <>

            <h1 style={{ marginBottom: "25px" }}>
                Dashboard
            </h1>

            <div className="dashboard-grid">

                <DashboardCard
                    title="Total Tasks"
                    value={dashboard.totalTasks}
                />

                <DashboardCard
                    title="To Do"
                    value={dashboard.todoTasks}
                />

                <DashboardCard
                    title="In Progress"
                    value={dashboard.inProgressTasks}
                />

                <DashboardCard
                    title="Completed"
                    value={dashboard.completedTasks}
                />

                <DashboardCard
                    title="Overdue"
                    value={dashboard.overdueTasks}
                />

            </div>

        </>

    );

}

export default Dashboard;