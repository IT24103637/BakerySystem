document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const categoryFilter = document.getElementById('categoryFilter');
    const inventoryTable = document.querySelector('.inventory-table tbody');
    const rows = inventoryTable.getElementsByTagName('tr');

    // Search functionality
    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        filterItems();
    });

    // Category filter
    categoryFilter.addEventListener('change', function() {
        filterItems();
    });

    function filterItems() {
        const searchTerm = searchInput.value.toLowerCase();
        const category = categoryFilter.value;

        for (let row of rows) {
            const name = row.cells[1].textContent.toLowerCase();
            const itemCategory = row.cells[2].textContent;

            const nameMatch = name.includes(searchTerm);
            const categoryMatch = category === '' || itemCategory === category;

            if (nameMatch && categoryMatch) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    }

    // Initialize data table if needed
    if (typeof $ !== 'undefined') {
        $('.inventory-table').DataTable({
            responsive: true,
            pageLength: 10,
            lengthMenu: [5, 10, 25, 50, 100]
        });
    }
});