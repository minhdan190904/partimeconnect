function previewMap() {
    const address = document.getElementById('workAddress')?.value;
    const lat = document.getElementById('latitude')?.value;
    const lng = document.getElementById('longitude')?.value;
    const frame = document.getElementById('mapFrame');

    if (!frame) return;

    if (lat && lng) {
        frame.src = `https://www.google.com/maps?q=${lat},${lng}&output=embed`;
        return;
    }

    if (address) {
        frame.src = `https://www.google.com/maps?q=${encodeURIComponent(address)}&output=embed`;
    }
}