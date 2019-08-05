import React, {Component} from 'react';
import { Card,CardHeader, CardBody, Row, Col, FormGroup, Input,  Button,
        Dropdown, DropdownToggle, DropdownMenu, DropdownItem,
     Modal, ModalHeader, ModalBody, ModalFooter, Alert } from 'reactstrap';
import { customerActions } from '../actions/customerActions';
import {connect} from 'react-redux';
import { FaEdit } from 'react-icons/fa';
import { Link} from 'react-router-dom';

class CustomerDetails extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedNote : "",
            selectedNoteTxt : "",
            modal: false,
            statusDropDownOpen : false

        }

        this.handleEditNoteClick = this.handleEditNoteClick.bind(this);
        this.handleEditNoteSaveClick = this.handleEditNoteSaveClick.bind(this);
        this.hideEditModal = this.hideEditModal.bind(this);
        this.handleStatusChange = this.handleStatusChange.bind(this);
        this.handleNotesChange = this.handleNotesChange.bind(this);
        this.toggleStatusDropDown = this.toggleStatusDropDown.bind(this);
        this.handleSaveNoteClick = this.handleSaveNoteClick.bind(this);
        this.handleNewNotesChange = this.handleNewNotesChange.bind(this);
    }

    componentWillMount() {
        let customerId = localStorage.getItem("customerId");
        this.getCustomerDetails( customerId );
    }

    getCustomerDetails( customerId ) {
        this.props.dispatch(customerActions.getCustomerDetails( customerId ));
    }

    handleEditNoteClick( note ) {
        this.setState({
            selectedNote : note,
            modal : true,
            selectedNoteTxt : note.content,
            newNoteContent : ""
        })
    }

    handleStatusChange( customer, status ) {
        this.props.dispatch(customerActions.updateCustomerStatus( customer , status));
    }

    hideEditModal() {
        this.setState({
          modal: false,
          selectedNote: "",
          selectedNoteTxt : "",
          statusDropDownOpen : false
        });
      }

    handleNotesChange( e ) {
        let notes = e.target.value;
        if(!notes) {
            notes = ""
        }
        let selectedNote = this.state.selectedNote;
        selectedNote.content = notes;
        this.setState( {
            selectedNoteTxt : notes
        })
    }

    handleEditNoteSaveClick() {
        this.props.dispatch(customerActions.updateNote(this.state.selectedNote));
        this.setState( {
            modal : false
        });
    }

    toggleStatusDropDown() {
        this.setState(prevState => ({
            statusDropDownOpen: !prevState.statusDropDownOpen
          }));
      }
    
      handleSaveNoteClick( customer ) {
        this.props.dispatch(customerActions.createNoteForCustomer( customer, this.state.newNoteContent ));
        this.setState({
            newNoteContent : ""
        });
      }

      handleNewNotesChange(e) {
        let notes = e.target.value;
        if(!notes) {
            notes = ""
        }        
        this.setState( {
            newNoteContent : notes
        })
      }
      
  render() {
    const { customers } = this.props;
    return (
        <div id="customerDetails" className="align-left" >
            <Row>
                <Col md="12" sm="12"  className= "mr-top-1" >
                    <Link to="/" color="info"> Back to customers</Link>
                </Col>

            </Row>
            {customers && customers.message && 
                <Alert color="success">
                        {customers.message}
                </Alert>
            }
            <Modal isOpen={this.state.modal} toggle={this.toggle} className={'modal-lg'}>
                <ModalHeader toggle={this.toggle}> Edit note</ModalHeader>
                <ModalBody>
                    <FormGroup>                    
                        <Input onChange={(e) => this.handleNotesChange(e)} type="textarea" name="text" id="editNoteText"  
                            value={this.state.selectedNoteTxt} style={{ height: 200 }} required maxLength="200"/>
                    </FormGroup>                
                </ModalBody>
                <ModalFooter>
                <Button color="primary" onClick={this.handleEditNoteSaveClick}>Save</Button>{' '}
                <Button color="secondary" onClick={this.hideEditModal}>Cancel</Button>
                </ModalFooter>
            </Modal>        
        
            {customers && customers.item &&
                <Card className="mr-top-1 align-left">
                    <CardHeader>

                        <strong> Customer Details </strong>
                    </CardHeader>
                    <CardBody>
                        <Row>
                            <Col md="4" sm="12">
                                <Row>
                                    <Col md="12" sm="12"  className="font-20">
                                        {customers.item.name}
                                    </Col>

                                </Row>
                                <Row>
                                    <Col md="12">{customers.item.emailAddress}</Col>
                                </Row>
                                <Row>
                                    <Col md="12">{customers.item.contactNumber}</Col>
                                </Row>
                                <Row>
                                    <Col md="12">Address</Col>
                                </Row>
                                <Row>
                                    <Col md="12">{customers.item.address}</Col>
                                </Row>
                                <Row className="mr-top-2">
                                    <Col md="3" sm="12">
                                        <strong>Status</strong>
                                    </Col>
                                    <Col md="9" sm="12">
                                        <Dropdown group isOpen={this.state.statusDropDownOpen} size="md" toggle={this.toggleStatusDropDown}>  
                                            <DropdownToggle  color="success" caret>
                                            {(() => {
                                                    switch ( customers.item.status ) {
                                                    case 'PROSPECTIVE': return 'Prospective';
                                                    case 'CURRENT': return 'Current';
                                                    case 'NON_ACTIVE': return 'Non-active';
                                                    default : return 'PROSPECTIVE';
                                                    }
                                                })()}
                                            </DropdownToggle>
                                            <DropdownMenu>                                          
                                                <DropdownItem onClick={()=> { this.handleStatusChange( customers.item, 'PROSPECTIVE') }}>Prospective</DropdownItem>
                                                <DropdownItem onClick={()=> { this.handleStatusChange( customers.item, 'CURRENT') }}>Current</DropdownItem>
                                                <DropdownItem onClick={()=> { this.handleStatusChange( customers.item, 'NON_ACTIVE') }}>Non-active</DropdownItem>
                                            </DropdownMenu>
                                        </Dropdown>
                                    </Col>
                                </Row>

                            </Col>
                            <Col md="8" sm="12">
                                <Row className="mr-bot-2">
                                    <Col md="12"><strong>Notes</strong></Col>
                                </Row>
                                {customers.item.notes && customers.item.notes.map( (note) => 
                                    <div className="customer-note">
                                        <Row className="pd-bot-1">
                                            <Col md="9">{note.content}</Col>
                                            <Col md="3"><Button color="primary" className="curson-pointer" onClick={() => this.handleEditNoteClick(note)}> <FaEdit/> </Button></Col>
                                        </Row>                                        
                                    </div>  
                                )}
                                
                                <Row>
                                    <Col md="9" sm= "12">
                                        <FormGroup>
                                            <Input type="textarea" name="text" onChange={(e) => this.handleNewNotesChange(e)} 
                                                    value={this.state.newNoteContent} id="createNoteText" style={{ height: 200 }} required maxLength="200" />
                                        </FormGroup>
                                    </Col>
                                    <Col md="3" sm="12">
                                        <Button color="primary" onClick={() => this.handleSaveNoteClick( customers.item)}>Save Note</Button>
                                    </Col>
                                </Row>
                            </Col>
                    </Row>
                    </CardBody>
                </Card>


            }
        </div>

    );
  }
}

function mapStateToProps(state) {
    const { customers, location } = state;
    return {
      customers,
      location
    };
  }
export default connect(mapStateToProps) (CustomerDetails);
