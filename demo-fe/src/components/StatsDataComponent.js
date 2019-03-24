import React, { Component } from 'react'
import PropTypes from 'prop-types';
import ReactTable from "react-table";
import "react-table/react-table.css";

export class StatsDataComponent extends Component {
  render() {
    const { maxToken, charsOccurrence, tokensOccurrence } = this.props.statsData;

    // ---------- tokens occurence columns -------------
    const tokensColumns = [{
      Header: 'Token',
      accessor: 'token'
    }, {
      Header: 'Occurrence',
      accessor: 'occurrence'
    }]

    // ---------- chars occurence columns -------------
    const charsColumns = [{
      Header: 'Letter',
      accessor: 'token'
    }, {
      Header: 'Occurrence',
      accessor: 'occurrence'
    }]

    return (
      <div className="dataContainer">
        <div className="tableDesc">Longest word [{maxToken.length} characters]:</div>
        <div className="content">{maxToken.token}</div>
        <div>
          <div className="tableDesc">Each word's frequency:</div>
          <ReactTable className="reactTable"
            data={tokensOccurrence}
            columns={tokensColumns}
            defaultPageSize={10}
            pageSizeOptions={[10, 30, 60, 100]}
          />

          <div className="tableDesc">Most common letters:</div>
          <ReactTable className="reactTable"
            data={charsOccurrence}
            columns={charsColumns}
            defaultPageSize={10}
            pageSizeOptions={[10, 30, 60, 100]}
          />
        </div>
      </div>
    );
  }
}

StatsDataComponent.propTypes = {
  statsData: PropTypes.object.isRequired
}

export default StatsDataComponent

