/* ==================== MOSTRAR/OCULTAR MENÚ MÓVIL ==================== */
const navMenu = document.getElementById('nav-menu'),
      navToggle = document.getElementById('nav-toggle'),
      navClose = document.getElementById('nav-close');

/*===== MOSTRAR MENÚ =====*/
// Valida si la constante existe
if (navToggle) {
    navToggle.addEventListener('click', () => {
        navMenu.classList.add('show-menu');
    });
}

/*===== OCULTAR MENÚ =====*/
// Valida si la constante existe
if (navClose) {
    navClose.addEventListener('click', () => {
        navMenu.classList.remove('show-menu');
    });
}

/* ==================== CERRAR MENÚ AL HACER CLIC EN UN ENLACE ==================== */
const navLinks = document.querySelectorAll('.nav__link');

function linkAction() {
    const navMenu = document.getElementById('nav-menu');
    // Cuando hacemos clic en cada nav__link, removemos la clase show-menu
    navMenu.classList.remove('show-menu');
}
navLinks.forEach(n => n.addEventListener('click', linkAction));

/* ==================== ACTUALIZAR AÑO EN EL FOOTER ==================== */
const yearSpan = document.getElementById('year');
if(yearSpan) {
    yearSpan.innerHTML = new Date().getFullYear();
}

/*
    NOTA: La función para cambiar el header al hacer scroll ha sido eliminada
    porque el nuevo diseño requiere que el header sea visible permanentemente.
*/


/* ==================== CONTROL DE TIEMPO DE INACTIVIDAD ==================== */
(function() {
    // Solo ejecutar este script si el usuario está autenticado.
    // Buscamos el botón de logout, que solo existe si el usuario está logueado.
    const logoutButton = document.querySelector('.logout-button');
    if (!logoutButton) {
        return;
    }

    let inactivityTimer;
    let warningTimer;
    const INACTIVITY_TIMEOUT = 60 * 1000; // 60 segundos para logout
    const WARNING_TIME = 45 * 1000;     // 45 segundos para mostrar advertencia

    // Función para cerrar la sesión
    const logoutUser = () => {
        // Usamos el formulario de logout para una salida segura
        const logoutForm = document.querySelector('form[action*="logout"]');
        if (logoutForm) {
            logoutForm.submit();
        } else {
            // Fallback por si no encuentra el formulario
            window.location.href = '/logout';
        }
    };

    // Función para mostrar el modal de advertencia
    const showWarningModal = () => {
        // Crear el modal si no existe
        if (!document.getElementById('inactivityModal')) {
            const modalHTML = `
                <div id="inactivityModal" class="inactivity-modal-overlay">
                    <div class="inactivity-modal">
                        <h3>¿Sigues ahí?</h3>
                        <p>Tu sesión está a punto de expirar por inactividad. Serás desconectado en breve.</p>
                        <div class="inactivity-modal-actions">
                            <button id="stayLoggedIn" class="button">Permanecer Conectado</button>
                        </div>
                    </div>
                </div>
            `;
            document.body.insertAdjacentHTML('beforeend', modalHTML);

            // Añadir evento al botón del modal
            document.getElementById('stayLoggedIn').addEventListener('click', resetInactivityTimer);
        }
        
        // Mostrar el modal
        document.getElementById('inactivityModal').classList.add('visible');
    };

    // Función para ocultar el modal
    const hideWarningModal = () => {
        const modal = document.getElementById('inactivityModal');
        if (modal) {
            modal.classList.remove('visible');
        }
    };

    // Función para reiniciar los temporizadores
    const resetInactivityTimer = () => {
        clearTimeout(inactivityTimer);
        clearTimeout(warningTimer);
        hideWarningModal();

        // Iniciar nuevos temporizadores
        warningTimer = setTimeout(showWarningModal, WARNING_TIME);
        inactivityTimer = setTimeout(logoutUser, INACTIVITY_TIMEOUT);
    };

    // Eventos que reinician el temporizador
    window.addEventListener('load', resetInactivityTimer);
    document.addEventListener('mousemove', resetInactivityTimer);
    document.addEventListener('keypress', resetInactivityTimer);
    document.addEventListener('click', resetInactivityTimer);
    document.addEventListener('scroll', resetInactivityTimer);

})();
