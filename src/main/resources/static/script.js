const todoaddicon = document.getElementById("openModal");
const closeModal = document.getElementById("closeModal");
const todolist = document.getElementById("todolist");
const modal = document.querySelector(".modal");

todoaddicon.addEventListener("click", function () {
  modal.classList.remove("hidden");
});
closeModal.addEventListener("click", function () {
  modal.classList.add("hidden");
});
document
  .getElementById("todo-form")
  .addEventListener("submit", async function (e) {
    e.preventDefault();
    const data = new FormData(e.target);
    const responce = await fetch("http://localhost:4000/todos", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        title: "todo title 2",
        discription: "todo discription 2",
      }),
    });
    if (responce.ok) modal.classList.add("hidden");
    loadData();
  });
const loadData = async function () {
  const responce = await fetch("http://localhost:4000/todos");
  const todos = await responce.json();
  todolist.innerHTML = "";
  todos.forEach((element) => {
    todolist.insertAdjacentHTML(
      "beforeend",
      `
<div class="todo-item">
  <div class="item-header">
    <p class="title">${element.title}</p>
    <div>
    <button class="btn-blue">Edit</button>
    <button class="btn-red">delete</button>
    </div>
  </div>
  <p class="discription">${element.discription}</p>
</div>
        `
    );
  });
};
window.onload = loadData();
