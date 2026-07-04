import Navbar from "./Navbar";
import Sidebar from "./Sidebar";

function Layout({ children }) {

    return (

        <div className="layout">

            <Sidebar />

            <div className="content">

                <Navbar />

                <main>

                    {children}

                </main>

            </div>

        </div>

    );

}

export default Layout;