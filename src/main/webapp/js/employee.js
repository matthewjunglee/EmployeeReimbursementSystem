function renderTable(reimbursements) {
  for (const reimb of reimbursements) {
    // create table row components
    const tr = document.createElement("tr");
    tr.setAttribute('style', 'background-color:gainsboro')
    
    const idTd = document.createElement("td");
    const submittedTd = document.createElement("td");
    const resolvedTd = document.createElement("td");
    const statusTd = document.createElement("td");
    const typeTd = document.createElement("td");
    const amountTd = document.createElement("td");
    const descriptionTd = document.createElement("td");

    // set innerText of the table data
    idTd.innerText = reimb.id;
    submittedTd.innerText = `${reimb.dateSubmitted.monthValue}-${reimb.dateSubmitted.dayOfMonth}-${reimb.dateSubmitted.year}`;
    statusTd.innerText = reimb.statusId.status;
    typeTd.innerText = reimb.typeId.type;
    amountTd.innerText = `$${reimb.amount}`;
    descriptionTd.innerText = reimb.description;

     // append table data to table row
     tr.append(idTd, submittedTd);

    // reimbursement date resolved
    if (reimb.dateResolved != null) {
      resolvedTd.innerText = `${reimb.dateResolved.monthValue}-${reimb.dateResolved.dayOfMonth}-${reimb.dateResolved.year}`;
      if (reimb.statusId.id == 1) { // reimbursement approved
        tr.setAttribute('class', 'table-success');
      } else { // reimbursement denied
        tr.setAttribute('class', 'table-danger');
      }
      tr.append(resolvedTd);
    }

    // append table data to table row
    tr.append(statusTd, typeTd, amountTd, descriptionTd);

    // set id for table data
    idTd.setAttribute('id', `id${reimb.id}`);
    // amountTd.setAttribute('id', `amount${reimb.id}`);
    // descriptionTd.setAttribute('id', `description${reimb.id}`);
    // typeTd.setAttribute('id', `type${reimb.id}`);
    
    if (reimb.statusId.id !== 0) { // Reimbursement is resolved
      document.getElementById("resolvedTableBody").append(tr);
    } else { // Reimbursement is pending
      // create delete button
      const deleteButton = document.createElement("button");
      deleteButton.innerText = "Delete";
      deleteButton.setAttribute('class', 'btn btn-outline-danger');

      // // create a form
      // const updateForm = document.createElement("form");
      // updateForm.setAttribute("action", "updateRequest.page");
      // updateForm.setAttribute("method", "POST");
      // // create a submit button for the form
      // const updateButton = document.createElement("input");
      // updateButton.setAttribute("type", "submit");
      // updateButton.setAttribute("value", "Update");
      // updateForm.append(updateButton);

      // add event listener to the delete button
      deleteButton.addEventListener('click', function() {deleteReimburesment(reimb.id)});

      // add buttons to table row
      // const updateTd = document.createElement("td");
      const deleteTd = document.createElement("td");
      // updateTd.append(updateForm);
      deleteTd.append(deleteButton);

      tr.append(/* updateTd, */ deleteTd);
      document.getElementById("pendingTableBody").append(tr);
    }
  }
}

async function asyncFetch(url, expression) {
  const response = await fetch(url, {
    method:"GET"
  });
  console.log(url);
  const json = await response.json();
  expression(json);
}

asyncFetch("http://54.241.47.22:8081/ERS-0.0.1-SNAPSHOT/reimbursements.json", renderTable);

// async function updateReimbursement(id) {
//   // console.log('in function');
//   console.log('reimbid', document.getElementById(`id${id}`).innerText);
//   console.log('amount', document.getElementById(`amount${id}`).innerText);
//   console.log('description', document.getElementById(`description${id}`).innerText);
//   console.log('type', document.getElementById(`type${id}`).innerText);

//   const reimbursement = {
//     id: document.getElementById(`id${id}`).innerText,
//     amount: document.getElementById(`amount${id}`).innerText,
//     description: document.getElementById(`description${id}`).innerText,
//     typeString: document.getElementById(`type${id}`).innerText
//     // typeId: {
//     //   type: document.getElementById(`type${id}`).innerText
//     // }
//   };
//   const fetched = await fetch("http://localhost:8080/ERS/update.page", {
//     method: "POST",
//     body: JSON.stringify(reimbursement)
//   });
//   // const json = await fetched.text();
//   // const rows = document.getElementById('pendingTableBody').innerHTML = '';
//   // asyncFetch("http://localhost:8080/ERS/employee.json", renderTable);
// }

// delete a request
async function deleteReimburesment(id) {
  const reimbursement = {
    id: document.getElementById(`id${id}`).innerText
  };
  
  await fetch("http://54.241.47.22:8081/ERS-0.0.1-SNAPSHOT/delete.json", {
    method: "POST",
    body: JSON.stringify(reimbursement)
  });
  document.getElementById('pendingTableBody').innerHTML = '';
  document.getElementById('resolvedTableBody').innerHTML = '';
  asyncFetch("http://54.241.47.22:8081/ERS-0.0.1-SNAPSHOT/reimbursements.json", renderTable);
}

// create a new request
async function createRequest() {
  await fetch("http://54.241.47.22:8081/ERS-0.0.1-SNAPSHOT/create.json", {
    method:"POST"
  });
}