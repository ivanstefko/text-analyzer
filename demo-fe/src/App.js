import React, { Component } from 'react';
import SearchComponent from './components/SearchComponent'
import StatsDataComponent from './components/StatsDataComponent'
import Header from './components/layout/Header'
import axios from 'axios';

import './App.css';

class App extends Component {
  state = {
    statsData: {},
    loaded: false,
    errorMessage: ''
  }

  getStats = (url) => {
    this.setState({ loaded: false });

    axios.get(`http://localhost:8080/api/searchAnalyze/?url=${url}`)
      .then(res => {
        
        this.setState({ 
          statsData: res.data,
          loaded: true,
          errorMessage: ''
        })

      }).catch(error => {
          var errorMessage = '';

          // Unknown Host Exception -> custom status code 522
          if (error && error.response && error.response.data && error.response.data.code === 522) {
            errorMessage = `Unknown host ${url}. Please check the url.`;
          } else {
            errorMessage = error.message;
          }

          this.setState({
            errorMessage: errorMessage,
            loaded: false
          })

        }
      );
  }

  render() {
    const { statsData, loaded, errorMessage } = this.state;
      return (
        <div className="appContainer">
          <Header />
          <SearchComponent search={this.getStats} />
          
          {loaded && !errorMessage && <StatsDataComponent statsData={statsData} /> }
          {errorMessage && <div className="error"> {errorMessage} </div>}

         </div>  
      );
  }
}

export default App;
