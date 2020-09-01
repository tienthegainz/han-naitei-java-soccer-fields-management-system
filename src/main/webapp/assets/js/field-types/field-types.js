const showToast = (status, message) => {
    $(document).Toasts('create', {
        class: `bg-${status} custom-toast`,
        title: status[0].toUpperCase() + status.substring(1),
        body: message,
        position: 'bottomLeft',
        autohide: true,
        delay: 4000
    });
};
