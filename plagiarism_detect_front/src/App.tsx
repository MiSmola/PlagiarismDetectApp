import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import FileUpload from "./components/FileUpload";
import RepotView from "./components/RepotView";

const App: React.FC = () => {
    return (
        <div className="container-fluid" >
            <div className="my-3">
                <h1>Plagiarism Detection Application</h1>
            </div>

            <FileUpload />
            <RepotView/>
        </div>
    );
}

export default App;