const TOAST_TYPE = {
    success: 'success',
    warning: 'warning',
    error: 'danger',
    default: 'dark'
};

const showToast = (status, message) => {
    $(document).Toasts('create', {
        class: `bg-${TOAST_TYPE[status]} custom-toast`,
        title: status[0].toUpperCase() + status.substring(1),   //  Capitalize first character
        body: message,
        position: 'bottomLeft',
        autohide: true,
        delay: 4000
    });
};
