function renderTable(reimbursements) {
  for (const reimb of reimbursements) {
    // create table row components
    const tr = document.createElement("tr");
    tr.setAttribute('style', 'background-color:gainsboro')

    const idTd = document.createElement("td");
    const empTd = document.createElement("td");
    const resolverTd = document.createElement("td");
    const submittedTd = document.createElement("td");
    const resolvedTd = document.createElement("td");
    const statusTd = document.createElement("td");
    const typeTd = document.createElement("td");
    const amountTd = document.createElement("td");
    const descriptionTd = document.createElement("td");

    // set innerText of the table data
    idTd.innerText = reimb.id;
    empTd.innerText = reimb.authorId;
    resolverTd.innerText = reimb.resolverId;
    submittedTd.innerText = `${reimb.dateSubmitted.monthValue}-${reimb.dateSubmitted.dayOfMonth}-${reimb.dateSubmitted.year}`;
    statusTd.innerText = reimb.statusId.status;
    typeTd.innerText = reimb.typeId.type;
    amountTd.innerText = reimb.amount;
    descriptionTd.innerText = reimb.description;

    // append table data to table row
    tr.append(idTd, empTd);
    
    // reimbursment resolver
    if (reimb.resolverId !== 0) {
      tr.append(resolverTd);
    }
    tr.append(submittedTd);

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
    
    // Reimbursement is resolved
    if (reimb.statusId.id !== 0) {
      document.getElementById("resolvedTableBody").append(tr);
    } else { // Reimbursemenet is pending
      // create approve/deny buttons
      const approveButton = document.createElement("button");
      approveButton.innerText = "Approve";
      approveButton.setAttribute('class', 'btn btn-outline-success');

      const denyButton = document.createElement("button");
      denyButton.innerText = "Deny";
      denyButton.setAttribute('class', 'btn btn-outline-danger');


      // add event listener to the approve/deny buttons
      approveButton.addEventListener('click', function() {updateStatus(reimb.id, 'APPROVED')});
      denyButton.addEventListener('click', function() {updateStatus(reimb.id, 'DENIED')});

      // add buttons to table row
      const approveTd = document.createElement("td");
      const denyTd = document.createElement("td");
      approveTd.append(approveButton);
      denyTd.append(denyButton);

      tr.append(approveTd, denyTd);
      document.getElementById("pendingTableBody").append(tr);
    }
  }
}

asyncFetch("http://localhost:8080/ERS/reimbursements.json", renderTable);

// fetch resource
async function asyncFetch(url, expression) {
  const response = await fetch(url, {
    method:"GET"
  });
  const json = await response.json();
  expression(json);
}

// approve/deny request
async function updateStatus(id, update) {
  const reimbursement = {
    id: document.getElementById(`id${id}`).innerText,
    typeString: update
  };

  await fetch("http://localhost:8080/ERS/approveDeny.json", {
    method: "POST",
    body: JSON.stringify(reimbursement)
  });
  document.getElementById('pendingTableBody').innerHTML = '';
  document.getElementById('resolvedTableBody').innerHTML = '';
  asyncFetch("http://localhost:8080/ERS/reimbursements.json", renderTable);
}