import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import FileUpload from "./components/FileUpload";
import RepotView from "./components/RepotView";
import {TableCell} from "@mui/material";
import React from "react";
import FileUploadTask from "./components/FileUploadTask";

const App: React.FC = () => {
    return (
        <div className="container-fluid" >
            <div className="my-3">
                <h1>Plagiarism Detection Application</h1>
            </div>

            <FileUpload />
            <p></p>
            <FileUploadTask />
            <RepotView/>
            <div>
                <p>Levenshtein edit distance - amount of operations necessary to transform one document to another. The higher number the lower plagiarism probability.</p>
                <p>Jaro-Winkler coefficient - value from 0 to 1, the closer to 1 the higher the probability of plagiarism.</p>
                <p>Cosine similarity - value from 0 to 1, the closer to 1 the higher the probability of plagiarism.</p>
            </div>
        </div>

    );
}

export default App;