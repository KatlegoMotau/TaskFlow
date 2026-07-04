import { exportCSV, exportPDF } from "../services/taskService";

function Export() {

    const downloadCSV = async () => {

        try {

            const blob = await exportCSV();

            const url = window.URL.createObjectURL(blob);

            const link = document.createElement("a");

            link.href = url;

            link.download = "tasks.csv";

            document.body.appendChild(link);

            link.click();

            link.remove();

            window.URL.revokeObjectURL(url);

        } catch (error) {

            console.error(error);

            alert("Failed to download CSV.");

        }

    };

    const downloadPDF = async () => {

        try {

            const blob = await exportPDF();

            const url = window.URL.createObjectURL(blob);

            const link = document.createElement("a");

            link.href = url;

            link.download = "TaskFlow_Report.pdf";

            document.body.appendChild(link);

            link.click();

            link.remove();

            window.URL.revokeObjectURL(url);

        } catch (error) {

            console.error(error);

            alert("Failed to download PDF.");

        }

    };

    return (

        <div>

            <h1>Export Tasks</h1>

            <p>

                Download your tasks as either a CSV spreadsheet
                or a PDF report.

            </p>

            <div style={{ marginTop: "30px" }}>

                <button
                    onClick={downloadCSV}
                    style={{ marginRight: "15px" }}
                >
                    Download CSV
                </button>

                <button
                    onClick={downloadPDF}
                >
                    Download PDF
                </button>

            </div>

        </div>

    );

}

export default Export;