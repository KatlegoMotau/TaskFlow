import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Tasks from "./pages/Tasks";

import ProtectedRoute from "./components/ProtectedRoute";
import Layout from "./components/layout/Layout";
import CreateTask from "./pages/CreateTask";
import EditTask from "./pages/EditTask";
import Export from "./pages/Export";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={<Login />}
                />

                <Route
                    path="/register"
                    element={<Register />}
                />

                <Route
                    path="/dashboard"
                    element={
                        <ProtectedRoute>
                            <Layout>
                                <Dashboard />
                            </Layout>
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/tasks"
                    element={
                        <ProtectedRoute>
                            <Layout>
                                <Tasks />
                            </Layout>
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/create-task"
                    element={
                        <ProtectedRoute>
                            <Layout>
                                <CreateTask />
                            </Layout>
                        </ProtectedRoute>
                    }
                />
                
                <Route
                    path="/edit-task/:id"
                    element={
                        <ProtectedRoute>
                            <Layout>
                                <EditTask />
                            </Layout>
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/export"
                    element={
                        <ProtectedRoute>
                            <Layout>
                                <Export />
                            </Layout>
                        </ProtectedRoute>
                    }
                />

            </Routes>

        </BrowserRouter>

    );

}

export default App;