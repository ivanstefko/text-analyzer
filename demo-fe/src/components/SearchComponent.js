import React, { Component } from 'react'
import PropTypes from 'prop-types';

export class SearchComponent extends Component {
    state = {
        url: '',
        urlError: ''
    };

    validateUrl = () => {
        var res = this.state.url.match(/(http(s)?:\/\/.)(www\.)?[-a-zA-Z0-9@:%._+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_+.~#?&//=]*)/g);
       
        if(res == null) {
            this.setState({ 
                urlError: "The URL is not valid! Valid form: e.g. http://www.bbc.com" 
            });
            return false; 
        }

        return true; 
    }

    onSubmit = (e) => {
        e.preventDefault();
        const isValid = this.validateUrl();

        if (isValid) {
            this.props.search(this.state.url);
            this.setState({ url: '' })
            this.setState({ urlError: '' })
        }
    }

    onChange = (e) => {
        this.setState({ 
            url: e.target.value 
        });

        if (this.state.url.length > 5) {
            this.setState({ urlError: '' });
        }
    }

    render() {
        return (
            <div>
                <form onSubmit={this.onSubmit} style={{ display: 'flex', marginBottom: '3px', marginTop: '3px'}}>
                    <input
                        type="text"
                        value={this.state.url}
                        placeholder="Enter the url..."
                        style={{ flex: '10', padding: '5px'}}
                        onChange={this.onChange}
                    />

                    <input
                        type="submit"
                        value="Submit"
                        style={{ flex: '1' }}
                        className="btn"
                    />
                </form>
                <div className="error">{this.state.urlError}</div>
            </div>
        );
    }
}

// PropTypes
SearchComponent.propTypes = {
    search: PropTypes.func.isRequired
}

export default SearchComponent
