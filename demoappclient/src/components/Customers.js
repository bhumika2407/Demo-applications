import React, {Component} from 'react';
import {connect} from 'react-redux';
import Moment from 'react-moment';
import { Table, FormGroup, InputGroup, 
  Dropdown, DropdownToggle, DropdownMenu, DropdownItem,
  Col, InputGroupAddon, Input, InputGroupText} from 'reactstrap';
import { customerActions } from '../actions/customerActions';


 class Customers extends Component {

    constructor(props) {
        super(props);
        this.state = {
            customers : "",
            filterDropdownOpen : false,
            sortDropdownOpen : false,
            selectedStatus : "",
            selectedSort : "",
            searchValue : ""
        }

        this.handleCustomerRowClick = this.handleCustomerRowClick.bind( this );
        this.toggleSortDropDown = this.toggleSortDropDown.bind( this );
        this.toggleFilterDropDown = this.toggleFilterDropDown.bind( this );
        this.handleStatusFilterChange  =this.handleStatusFilterChange.bind( this );
        this.handleSearch = this.handleSearch.bind(this);
        this.handleSearchClick  =this.handleSearchClick.bind(this);
        this.handleSortByChange = this.handleSortByChange.bind(this);
    }

    componentDidMount() {
        this.getCustomers();
    }

    getCustomers() {
      this.props.dispatch(customerActions.getCustomers());
    }

    handleSearch( e ) {
      let searchTerm = e.target.value;
      this.setState({searchValue: searchTerm})
      this.props.dispatch(customerActions.getCustomersByParams( searchTerm, this.state.selectedStatus,this.state.selectedSort));
    }

    handleStatusFilterChange( selectedStatus ) {
      this.setState({
        selectedStatus: selectedStatus
      });
      this.props.dispatch(customerActions.getCustomersByParams(this.state.searchValue, selectedStatus,this.state.selectedSort));
    }

    handleSortByChange( selectedSort ) {
      this.setState({
        selectedSort: selectedSort
      });
      this.props.dispatch(customerActions.getCustomersByParams(this.state.searchValue, this.state.selectedStatus,selectedSort));
    }

    handleSearchClick() {
      this.props.dispatch(customerActions.getCustomersByParams(this.state.searchValue, this.state.selectedStatus, this.state.selectedSort));
    }

    handleCustomerRowClick( customerId ) {
      localStorage.setItem("customerId" , customerId);
      this.props.history.push('/customer/details');
    }

    toggleSortDropDown() {
      this.setState(prevState => ({
        sortDropdownOpen: !prevState.sortDropdownOpen
      }));
    }

    toggleFilterDropDown() {
      this.setState(prevState => ({
        filterDropdownOpen: !prevState.filterDropdownOpen
      }));
    }
   
  render() {
    const { customers } = this.props;
    const { sortBy, selectedStatus }  = this.state;

    return (
      <div className="animated fadeIn">
             

              <FormGroup row className="mr-top-2">
                  <Col md="9" xs="12">
                      <InputGroup>
                        <Input type="text" onChange={this.handleSearch} placeholder="Search a customer by name eg. Transport" value={this.state.searchValue}/>
                          <InputGroupAddon addonType="append">
                              <InputGroupText onClick={this.handleSearchClick}> Search </InputGroupText>
                          </InputGroupAddon> 
                      </InputGroup>
                  </Col>
                  <Col md="3" xs="12" className="align-right">
                    <Dropdown group isOpen={this.state.filterDropdownOpen} size="md" toggle={this.toggleFilterDropDown}  className="mr-left-1 mr-right-2">  
                      <DropdownToggle caret>
                        {/* TODO: Get the status list to get from server */}
                      {(() => {
                            switch ( selectedStatus ) {
                              case 'PROSPECTIVE': return 'Prospective';
                              case 'CURRENT': return 'Current';
                              case 'NON_ACTIVE': return 'Non-active';
                              default  : return 'All Statuses';
                            }
                          })()}
                      </DropdownToggle>
                      <DropdownMenu>
                          <DropdownItem onClick={()=> { this.handleStatusFilterChange('') }}>All Statuses</DropdownItem>
                          <DropdownItem onClick={()=> { this.handleStatusFilterChange('PROSPECTIVE') }}>Prospective</DropdownItem>
                          <DropdownItem onClick={()=> { this.handleStatusFilterChange('CURRENT') }}>Current</DropdownItem>
                          <DropdownItem onClick={()=> { this.handleStatusFilterChange('NON_ACTIVE') }}>Non-active</DropdownItem>
                      </DropdownMenu>
                    </Dropdown>
                    <Dropdown group isOpen={this.state.sortDropdownOpen} size="md" data-toggle="dropdown" toggle={this.toggleSortDropDown}>
                      <DropdownToggle caret>
                        Sort By 
                      {(() => {
                            switch (sortBy) {                              
                              case 'name': return 'Name';
                              case 'status': return 'Status';
                              default : return ''
                            }
                          })()}
                      </DropdownToggle>
                      <DropdownMenu>
                          <DropdownItem onClick={()=> { this.handleSortByChange('name') }}>Name</DropdownItem>
                          <DropdownItem onClick={()=> { this.handleSortByChange('status') }}>Status</DropdownItem>
                      </DropdownMenu>
                    </Dropdown>

                    
                  </Col>
            </FormGroup>

        <Table hover>
            <thead>
              <tr className="react-grid-header">
                <th>Customer Id</th>
                <th>Name</th>
                <th>Contact Person</th>
                <th>Status</th>
                <th>Email Address</th>
                <th>Contact Number</th>
                <th>Address</th>
                <th>Created on</th>
              </tr>
            </thead>
            <tbody>
        {customers && customers.items && customers.items.map((customer) => 
            
              <tr className ="cursor-pointer" onClick={() => this.handleCustomerRowClick( customer.id )} >
                <td>{customer.id}</td>
                <td>{customer.name}</td>
                <td>{customer.contactPersonName}</td>
                <td>{customer.status}</td>
                <td>{customer.emailAddress}</td>
                <td>{customer.contactNumber}</td>
                <td>{customer.address}</td>
                <td><Moment format="DD-mm-YYYY hh:mm a">{customer.creationDate}</Moment></td>
              </tr>
            
        )}
          </tbody>
        </Table>
        </div>
    );
  }
}

function mapStateToProps(state) {
  const { customers } = state;
  return {
    customers
  };
}

export default connect(mapStateToProps)(Customers);