import React, { Component } from 'react';
import '../css/App.css';

class Main extends Component {
    constructor() {
        super();
        this.state = {
            inputVal:     '',
            outputVal:    '',
            errorMessage: ''
        };
        this.setInputVal = this.setInputVal.bind(this);
        this.count = this.count.bind(this);
        this.splitText = this.splitText.bind(this);
        this.setErrorMessage = this.setErrorMessage.bind(this);
        this.setOutputVal = this.setOutputVal.bind(this);
    }

    count = function(evt) {
        evt.preventDefault();
        this.setErrorMessage('');
        this.setOutputVal('');
        const url = "http://localhost:8080/count";
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({data: this.splitText()})
        })
            .then(res => {
                if (res.ok) {
                    return res.json()
                } else {
                    console.log(res);
                    throw new Error(res);
                }
            })
            .then(res => {
                for (let i in res) {
                    this.setOutputVal(this.state.outputVal + i + " - " + res[i] + "\n");
                }
        })
            .catch(error => {
                console.log(error);
                this.setErrorMessage(error.toString());
            });
    };

    splitText = function() {
        let text = this.state.inputVal;
        return text.split(' ');
    };

    setInputVal = function(evt) {
        this.setState({
            inputVal: evt.target.value
        });
    };

    setOutputVal = function(val) {
        this.setState({
            outputVal: val
        });
    };

    setErrorMessage = function(message) {
        this.setState({
            errorMessage: message
        })
    };

    render() {
        return (
            <div className="Main">
                <form>
                    <textarea
                        value = { this.state.inputVal }
                        onChange = { this.setInputVal }>
                    </textarea>
                    <textarea
                        disabled = { true }
                        value = { this.state.outputVal }>
                    </textarea>
                    <button onClick = { this.count }>Proceed</button>
                </form>
                <p>{ this.state.errorMessage }</p>
            </div>
        );
    }
}

export default Main;